FROM maven:3.8.1-jdk-8-slim as builder

MAINTAINER kevin

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# 声明环境变量，这样容器就可以在运行时访问它们
ENV OPEN_AI_MODEL=gpt-3.5-turbo
ENV OPEN_AI_KEY=你的KEY
ENV OPEN_AI_ORG=你的Org
ENV OPEN_TOKEN=你的Token

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","/app/target/magic.jar","--spring.profiles.active=prod"]