FROM icr.io/appcafe/open-liberty:beta-instanton

USER 0

RUN set -eu; \
    dnf install -y vim wget; \
    dnf clean all;

RUN cd / \
 && mkdir /work \
 && chown -R 1001:0 /work \
 && cd /work \
 && wget --progress=dot:mega -O semeruInstantOn.tar.gz https://github.com/ibmruntimes/semeru17-ea-binaries/releases/download/jdk-17.0.5%2B2_august_22-preview_3/ibm-semeru-open-ea-jdk_x64_linux_17.0.5_2_august_22-preview_3.tar.gz \
 && tar -xzf semeruInstantOn.tar.gz \
 && mv jdk-17.0.5+2 semeruInstantOn \
 && rm -rf semeruInstantOn.tar.gz

ENV JAVA_HOME=/work/semeruInstantOn
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /work/

COPY --chown=1001:0 helloworld /work/helloworld/
COPY --chown=1001:0 scripts /work/scripts/

RUN chmod a+rx /work/scripts/*

RUN cd helloworld \
    && ./mvnw -DskipTests package

RUN chown -R 1001:0 helloworld/target

USER 1001

ENTRYPOINT ["./scripts/entry-point.sh"]

