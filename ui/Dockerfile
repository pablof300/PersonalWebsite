FROM mhart/alpine-node:12

RUN mkdir persian
RUN mkdir persian/ui
RUN ls

COPY /build ./persian/ui/build
WORKDIR ./persian/ui/build
CMD ["npx", "serve", "-s", "-l", "443"]
