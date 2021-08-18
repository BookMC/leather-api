FROM alpine as actions
ADD ./actions/leather-api.tar /src/api/

FROM amd64/openjdk:16
WORKDIR /src/bot
COPY --from=actions /src/api/leather-api-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/leather-api" ]