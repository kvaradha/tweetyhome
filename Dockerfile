FROM adoptopenjdk/openjdk11
ARG JAR_FILE=tweetyhome-2.7.0.jar
COPY target/${JAR_FILE} tweetyhome.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=kubedeployment","/tweetyhome.jar"]
