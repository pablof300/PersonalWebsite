FROM mhart/alpine-node:12

RUN mkdir persian
RUN mkdir persian/ui
RUN ls

COPY /build ./persian/ui/build
WORKDIR ./persian/ui/build
CMD ["npx", "serve", "-s", "--ssl-cert", "/ssl/962B91F0D924EA9F1D50018AC9D40B8CCF6D3993.crt", "--ssl-key", "/ssl/962B91F0D924EA9F1D50018AC9D40B8CCF6D3993.prv", "-l", "443"]