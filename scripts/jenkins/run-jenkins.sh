#!/bin/bash -pl

SCRIPT_DIR=`dirname "$0"`

jenkins_bin=$HOME/apps/jenkins/current

exec java -Xmx256m -DJENKINS_HOME=$HOME/var/jenkins \
-Dhudson.security.ArtifactsPermission=true \
-jar $jenkins_bin/jenkins.war \
--httpPort=8088 --httpsPort=-1 --http2Port=-1 \
--extraLibFolder=$jenkins_bin "$@"

