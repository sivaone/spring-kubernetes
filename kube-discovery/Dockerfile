FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="Siva Nagi Reddy ALLA <siva.alla@outlook.com>"

WORKDIR /opt
COPY target/kube-discovery-1.0-SNAPSHOT.jar /opt/app.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar