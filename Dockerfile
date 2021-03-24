FROM maven:3.5.3-jdk-8-alpine
ARG API_BUILD_DIR=/opt/usr/src
COPY . ${API_BUILD_DIR}
WORKDIR ${API_BUILD_DIR}
RUN mvn clean install


