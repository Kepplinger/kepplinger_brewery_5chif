FROM jboss/wildfly:latest

RUN /opt/jboss/wildfly/bin/add-user.sh admin passme --silent

COPY customization /opt/jboss/wildfly/customization

COPY target/docker-brewery.war /opt/jboss/wildfly/customization/docker-brewery.war

CMD ["/opt/jboss/wildfly/customization/execute.sh"]