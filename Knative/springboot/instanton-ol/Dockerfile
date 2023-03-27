FROM icr.io/appcafe/open-liberty:beta-instanton

USER 0

RUN set -eu; \
    dnf install -y vim wget; \
    dnf clean all;

RUN cd / \
 && mkdir /work \
 && chown -R 1001:0 /work \
 && cd /work \
 && wget --progress=dot:mega -O semeruInstantOn.tar.gz https://github.com/ibmruntimes/semeru17-ea-binaries/releases/download/jdk-17.0.6%2B10_march_23-preview_1/ibm-semeru-open-jdk_x64_linux_17.0.6_10_March_23-preview_1.tar.gz \
 && mkdir semeruInstantOn \
 && tar -xzf semeruInstantOn.tar.gz -C semeruInstantOn --strip-components 1\
 && rm -rf semeruInstantOn.tar.gz

ENV JAVA_HOME=/work/semeruInstantOn
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /work/

COPY --chown=1001:0 helloworld /work/helloworld/
COPY --chown=1001:0 server.xml /work/server.xml

RUN cd helloworld \
 && ./mvnw -DskipTests package

RUN chown -R 1001:0 helloworld/target

USER 1001

RUN cp ./helloworld/target/helloworld-0.0.1-SNAPSHOT.war /config/apps
RUN cp ./server.xml /config

RUN configure.sh
