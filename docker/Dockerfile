#
# Copyright (c) 2018 Dell Inc., or its subsidiaries. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#

ARG DOCKER_REGISTRY
FROM ${DOCKER_REGISTRY:+$DOCKER_REGISTRY/}eclipse-temurin:17-jdk-noble AS build
RUN mkdir /zu
COPY zu /zu
WORKDIR /zu
RUN ./gradlew --console=verbose --info shadowJar
RUN mv /zu/build/libs/zu-all.jar /zu/build/libs/zu.jar

FROM ${DOCKER_REGISTRY:+$DOCKER_REGISTRY/}zookeeper:3.9.5 AS final
COPY bin /usr/local/bin
RUN chmod +x /usr/local/bin/*
COPY --from=build /zu/build/libs/zu.jar /opt/libs/

RUN apt-get -q update && \
    apt-get install -y dnsutils curl procps socat
