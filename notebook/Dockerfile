FROM adoptopenjdk:11-jre-hotspot
MAINTAINER Siva Nagi Reddy Alla (siva.alla@outlook.com)

WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/notebook-0.0.1-SNAPSHOT.jar /opt/notebook.jar
ENTRYPOINT exec java $JAVA_OPTS -jar notebook.jar
