#!/bin/bash

# Usage: execute.sh [WildFly mode] [configuration file]
#
# The default mode is 'standalone' and default configuration is based on the
# mode. It can be 'standalone.xml' or 'domain.xml'.

echo "=> Executing Customization script"

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

MYSQL_CONTAINER_PORT_3306_TCP_ADDR=db
MYSQL_CONTAINER_PORT_3306_TCP_PORT=3306
MYSQL_DATABASE=keppidb
MYSQL_USER=brewery
MYSQL_PASSWORD=passme

CONNECTION_URL=jdbc:mysql://$MYSQL_CONTAINER_PORT_3306_TCP_ADDR:$MYSQL_CONTAINER_PORT_3306_TCP_PORT/$MYSQL_DATABASE
echo "Connection URL:" $CONNECTION_URL

echo "=> Hosts"
cat /etc/hosts
echo
echo "=> Umgebungsvariablen (env)"
env

function wait_for_server() {
  until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
  done
}

echo "=> Starting WildFly server"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG > /dev/null &
# $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &

echo "=> Waiting for the server to boot"
wait_for_server


$JBOSS_CLI -c << EOF
batch

# Add MySQL module
module add --name=com.mysql --resources=/opt/jboss/wildfly/customization/mysql-connector-java-5.1.42-bin.jar --dependencies=javax.api,javax.transaction.api

# Add MySQL driver
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)

# Add the datasource
data-source add --name=MysqlDS --driver-name=mysql --jndi-name=java:jboss/datasources/MysqlDS --connection-url=$CONNECTION_URL?useUnicode=true&amp;characterEncoding=UTF-8 --user-name=$MYSQL_USER --password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true

# Execute the batch
run-batch
EOF

# Deploy the WAR
echo "=> Deploy the WAR"
cp /opt/jboss/wildfly/customization/docker-brewery.war $JBOSS_HOME/$JBOSS_MODE/deployments/docker-brewery.war

echo "=> Shutting down WildFly"
if [ "$JBOSS_MODE" = "standalone" ]; then
  $JBOSS_CLI -c ":shutdown"
else
  $JBOSS_CLI -c "/host=*:shutdown"
fi

echo "=> Restarting WildFly"
# $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG > /dev/null
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -c $JBOSS_CONFIG