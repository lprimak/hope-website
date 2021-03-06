#!/bin/bash -p

SCRIPT_DIR=`dirname "$0"`

$SCRIPT_DIR/../hope-website-scripts/import-certs.sh 2>/dev/null

asadmin -I=false multimode << EOF
set configs.config.server-config.network-config.network-listeners.network-listener.http-listener-2.enabled=false
set configs.config.server-config.network-config.network-listeners.network-listener.http-listener-2.enabled=true
EOF

# haproxy
echo -e "set ssl cert $HOME/var/ssl-links/fullchain.pem <<\n$(cat $HOME/var/ssl-links/fullchain.pem*)\n" | \
socat tcp-connect:localhost:9999 -
echo "commit ssl cert $HOME/var/ssl-links/fullchain.pem" | socat tcp-connect:localhost:9999 -

