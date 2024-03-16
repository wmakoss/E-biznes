FROM ubuntu:22.04

ENV TZ = "Europe/Warsaw"

RUN apt-get update

RUN apt-get install -y software-properties-common

RUN add-apt-repository -y ppa:deadsnakes/ppa

RUN apt-get update

RUN env DEBIAN_FRONTEND="noninteractive" apt-get install -y python3.8

CMD ["python3.8", "--version"]
