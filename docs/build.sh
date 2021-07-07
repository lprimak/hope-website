#!/bin/bash -p
curl -s "https://get.sdkman.io" | bash
. "$HOME/.sdkman/bin/sdkman-init.sh"
sdk i java
sdk i jbake

jbake -b 
