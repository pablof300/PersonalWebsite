FROM ubuntu:18.04

RUN mkdir persian
COPY ./lib ./persian/lib
COPY ./service ./persian/service
COPY ./ui ./persian/ui
COPY ./scheduler ./persian/scheduler
COPY ./persian-services ./persian/persian-services

RUN ls persian
