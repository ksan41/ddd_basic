#!/usr/bin/env bash
# Use this script to wait for another service to become available

TIMEOUT=15
QUIET=0
HOST=127.0.0.1
PORT=3306
shift 2
CMD="$@"

echo "Waiting for $HOST:$PORT to be available..."

for i in $(seq 0 $TIMEOUT); do
    nc -z $HOST $PORT > /dev/null 2>&1
    result=$?

    if [ $result -eq 0 ]; then
        echo "$HOST:$PORT is available!"
        exec $CMD
    fi

    sleep 1
done

echo "Timeout! $HOST:$PORT is still not available."
exit 1
