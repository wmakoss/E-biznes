FROM ubuntu:22.04

ENV TZ = "Europe/Warsaw"

RUN apt-get update

RUN apt-get install -y software-properties-common

RUN add-apt-repository -y ppa:deadsnakes/ppa

RUN apt-get update

RUN env DEBIAN_FRONTEND="noninteractive" apt-get install -y python3.8

RUN apt-get install -y openjdk-8-jdk

RUN apt-get install -y unzip

RUN apt-get install -y zip

RUN apt-get install -y curl

RUN curl -s "https://get.sdkman.io" | bash

RUN bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install kotlin && sdk install gradle 8.6"

COPY ./application /root/application

WORKDIR /root/application

CMD ["/root/.sdkman/candidates/gradle/8.6/bin/gradle", "run"]
