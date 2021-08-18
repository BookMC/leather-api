FROM alpine as actions
ADD ./actions/leather-api.tar /src/bot/

FROM amd64/openjdk:16
WORKDIR /src/bot
COPY --from=actions /src/bot/leather-api-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/leather-api" ]