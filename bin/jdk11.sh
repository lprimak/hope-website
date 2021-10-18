#!/bin/bash -pl

sdk use java 11.0.12-zulu >/dev/null 2>&1
exec "$@"
