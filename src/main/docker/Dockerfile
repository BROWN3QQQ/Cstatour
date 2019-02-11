FROM java:8
VOLUME /tmp
ADD cstatour-0.0.1-SNAPSHOT.jar /cstatour-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /cstatour-0.0.1-SNAPSHOT.jar'
ENV JAVA_OPTS=""
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cstatour-0.0.1-SNAPSHOT.jar"]