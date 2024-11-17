#!/bin/bash

# Colors
RESET="\033[0m"
GREEN="\033[1;32m"
RED="\033[1;31m"
YELLOW="\033[1;33m"
BLUE="\033[1;34m"

# Emojis
ROCKET="🚀"
STOP_SIGN="🛑"
REFRESH="🔄"
CHECK_MARK="✅"
WARNING="⚠️"

# Configuration
JAR_FILE="validate-NEW.jar"
HOST="0.0.0.0"
PORT="8083"
LOG_DIR="logs"
LOG_FILE="$LOG_DIR/validate-$(date +'%Y-%m-%d_%H-%M-%S').log"
PID_FILE="validate.pid"

start_app() {
  echo -e "${BLUE}${ROCKET} Starting Validate...${RESET}"

  # Ensure the log directory exists
  mkdir -p "$LOG_DIR"

  # Check if the JAR file exists
  if [[ ! -f "$JAR_FILE" ]]; then
    echo -e "${RED}${WARNING} Error: $JAR_FILE not found in the current directory.${RESET}"
    exit 1
  fi

  # Start the application and save logs
  java -jar "$JAR_FILE" "$HOST" "$PORT" >> "$LOG_FILE" 2>&1 &
  APP_PID=$!
  echo "$APP_PID" > "$PID_FILE"

  echo -e "${GREEN}${CHECK_MARK} Validate is running with PID $APP_PID.${RESET}"
  echo -e "${YELLOW}Logs are being saved to $LOG_FILE.${RESET}"
  echo -e "${BLUE}Access Validate at http://$HOST:$PORT${RESET}"
}

stop_app() {
  echo -e "${RED}${STOP_SIGN} Stopping Validate...${RESET}"

  if [[ ! -f "$PID_FILE" ]]; then
    echo -e "${RED}${WARNING} Error: PID file not found. Is Validate running?${RESET}"
    exit 1
  fi

  APP_PID=$(cat "$PID_FILE")
  if kill "$APP_PID" > /dev/null 2>&1; then
    echo -e "${GREEN}${CHECK_MARK} Validate stopped successfully.${RESET}"
    rm -f "$PID_FILE"
  else
    echo -e "${RED}${WARNING} Error: Failed to stop Validate. It may not be running.${RESET}"
  fi
}

case "$1" in
  start)
    start_app
    ;;
  stop)
    stop_app
    ;;
  restart)
    echo -e "${YELLOW}${REFRESH} Restarting Validate...${RESET}"
    stop_app
    start_app
    ;;
  *)
    echo -e "${YELLOW}Usage: $0 {start|stop|restart}${RESET}"
    exit 1
    ;;
esac
