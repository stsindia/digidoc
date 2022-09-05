#!/usr/bin/env bash

NS=ddews
APP_NAME=qrcode
IMAGE_NAME=505604792624.dkr.ecr.us-east-1.amazonaws.com/dddigidoc:latest

mvn -DskipTests=true clean package spring-boot:build-image -Dspring-boot.build-image.imageName=${IMAGE_NAME}
