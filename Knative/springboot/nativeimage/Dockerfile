FROM registry.access.redhat.com/ubi8/ubi:latest

RUN set -eu; \
    dnf install -y \
       wget tar vim gcc zlib*; \
    dnf clean all;

WORKDIR /work/

RUN wget --progress=dot:mega -O graalCE17.tar.gz https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.2.0/graalvm-ce-java17-linux-amd64-22.2.0.tar.gz \
 && tar -xzf graalCE17.tar.gz \
 && rm -rf graalCE17.tar.gz

ENV JAVA_HOME=/work/graalvm-ce-java17-22.2.0
ENV GRAALVM_HOME=$JAVA_HOME
ENV PATH=$JAVA_HOME/bin:$PATH

RUN gu install native-image

COPY --chown=1001:0 helloworld /work/helloworld/

RUN cd helloworld \
    && ./mvnw -Pnative -DskipTests package

RUN chown -R 1001:0 helloworld/target

USER 1001

CMD ["./helloworld/target/helloworld", "-Xmx128m", "-Xms128m", "-Xmn100m" ]
