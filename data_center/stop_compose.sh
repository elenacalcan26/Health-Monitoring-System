#!/bin/sh

docker compose -f docker-compose.yml -f docker-compose.secrets.yml down -v
