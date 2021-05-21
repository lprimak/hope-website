#!/bin/bash -pl

SCRIPT_DIR=`dirname "$0"`

jenkins_bin=$HOME/apps/jenkins/current

exec java -Xmx256m -DJENKINS_HOME=$HOME/var/jenkins -jar $jenkins_bin/jenkins.war \
--httpPort=-1 --httpsPort=-1 --http2Port=8081 \
--extraLibFolder=$jenkins_bin "$@"

