FROM ubuntu:22.04

ENV TZ = "Europe/Warsaw"

RUN apt-get update

RUN apt-get install -y software-properties-common

RUN apt-get update

RUN apt-get install -y openjdk-17-jdk

# RUN apt-get install -y unzip

# RUN apt-get install -y zip

# RUN apt-get install -y curl

# RUN curl -s "https://get.sdkman.io" | bash

# RUN bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install gradle 8.6"

COPY ./application /root/application

WORKDIR /root/application

EXPOSE 80

CMD ["./gradlew", "bootRun"]
# CMD ["/root/.sdkman/candidates/gradle/8.6/bin/gradle", "run"]
