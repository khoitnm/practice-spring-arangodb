#!/bin/bash
docker-compose -f ./arangodb/docker-compose.yml down -v

# `down` command will stop your containers, but it also removes the stopped containers as well as any networks that were created.
# You can take `down` 1 step further and add the -v flag to remove all volumes too. This is great for doing a full blown reset on your environment by running `docker-compose down -v`