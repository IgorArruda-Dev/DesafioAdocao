FROM node:21 as builder
WORKDIR /usr/src/app
COPY . ./

RUN npx pnpm i
RUN  npx pnpm run build

FROM node:21-alpine3.17
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app .

EXPOSE 3000

CMD ["npx", "next", "start"]