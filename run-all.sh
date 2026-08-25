#!/usr/bin/env bash
set -Eeuo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
LOG_DIR="${ROOT_DIR}/.run-logs"
mkdir -p "$LOG_DIR"

pids=()

cleanup() {
    trap - EXIT INT TERM
    if ((${#pids[@]})); then
        kill "${pids[@]}" 2>/dev/null || true
        wait "${pids[@]}" 2>/dev/null || true
    fi
}
trap cleanup EXIT INT TERM

wait_for_port() {
    local name="$1"
    local port="$2"
    local attempts="${3:-60}"

    printf 'Aguardando %s na porta %s' "$name" "$port"
    while ! (echo > "/dev/tcp/127.0.0.1/$port") >/dev/null 2>&1; do
        ((attempts--))
        if ((attempts == 0)); then
            printf '\n%s não iniciou. Consulte %s/%s.log\n' "$name" "$LOG_DIR" "$name" >&2
            return 1
        fi
        printf '.'
        sleep 1
    done
    printf ' pronto\n'
}

start_service() {
    local name="$1"
    local module="$2"
    local main_class="$3"

    printf 'Iniciando %s\n' "$name"
    (
        cd "$ROOT_DIR"
        mvn -q -pl "$module" spring-boot:run \
            -Dspring-boot.run.main-class="$main_class" \
            >"$LOG_DIR/$name.log" 2>&1
    ) &
    pids+=("$!")
}

start_service discovery discovery com.bruno10log.discovery.DiscoveryApplication
wait_for_port discovery 8761 60

start_service config-server config-server com.bruno10log.configserver.ConfigServerApplication
if ! wait_for_port config-server 8888 15; then
    printf 'Continuando sem Config Server; os clientes usarão a configuração local.\n'
fi

start_service customer customer com.bruno10log.customer.CustomerApplication
start_service order order com.bruno10log.order.OrderApplication
start_service gateway gateway com.bruno10log.gateway.GatewayApplication

printf '\nTodos os serviços foram iniciados. Logs em %s\n' "$LOG_DIR"
printf 'Pressione Ctrl+C para encerrar todos.\n'

wait -n "${pids[@]}"
printf '\nUm serviço foi encerrado; finalizando os demais.\n'
exit 1
