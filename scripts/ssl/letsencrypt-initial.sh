#!/bin/bash -p

SCRIPT_DIR=`dirname "$0"`

exec $SCRIPT_DIR/letsencrypt-common.sh certonly \
-d hope.nyc.ny.us,apps.hope.nyc.ny.us,mail.hope.nyc.ny.us,\
mini.hope.nyc.ny.us,jenkins.hope.nyc.ny.us,admin.hope.nyc.ny.us "$@"
