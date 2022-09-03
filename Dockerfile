FROM alpine as actions
ADD ./actions/metadata.tar /src/api/

FROM eclipse-temurin:18.0.2.1_1-jre
WORKDIR /src/bot
COPY --from=actions /src/api/metadata-* /src/api
RUN apt update && apt install xargs
ENTRYPOINT [ "sh", "/src/api/bin/metadata" ]