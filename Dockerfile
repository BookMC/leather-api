FROM alpine as actions
ADD ./actions/metadata.tar /src/api/

FROM amd64/openjdk:16
WORKDIR /src/bot
COPY --from=actions /src/api/metadata-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/metadata" ]