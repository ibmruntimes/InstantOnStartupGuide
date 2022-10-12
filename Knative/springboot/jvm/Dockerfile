FROM registry.access.redhat.com/ubi8/ubi:latest

RUN set -eu; \
    dnf install -y \
# Semeru dependencies
       tzdata openssl wget ca-certificates fontconfig glibc-langpack-en tar vim; \
    dnf clean all;

WORKDIR /work/

RUN wget --progress=dot:mega -O semeruJDK17.tar.gz https://github.com/ibmruntimes/semeru17-binaries/releases/download/jdk-17.0.4.1%2B1_openj9-0.33.1/ibm-semeru-open-jdk_x64_linux_17.0.4.1_1_openj9-0.33.1.tar.gz \
 && tar -xzf semeruJDK17.tar.gz \
 && rm -rf semeruJDK17.tar.gz

ENV JAVA_HOME=/work/jdk-17.0.4.1+1
ENV PATH=$JAVA_HOME/bin:$PATH

COPY --chown=1001:0 helloworld /work/helloworld/

RUN cd helloworld \
    && ./mvnw -DskipTests package

RUN chown -R 1001:0 helloworld/target

USER 1001

CMD ["java", "-Xmx128m", "-Xms128m", "-Xmn100m", "-jar", "./helloworld/target/helloworld-0.0.1-SNAPSHOT.jar" ]
