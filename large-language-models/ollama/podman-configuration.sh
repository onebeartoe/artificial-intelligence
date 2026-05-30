
systemctl --user enable podman.socket --now
export DOCKER_HOST=unix://$(podman info --format '{{.Host.RemoteSocket.Path}}')
export TESTCONTAINERS_RYUK_DISABLED=true
