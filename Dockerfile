FROM adoptopenjdk/openjdk11
ARG JAR_FILE=tweetyhome-2.7.0.jar
COPY ${JAR_FILE} tweetyhome.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/tweetyhome.jar"]