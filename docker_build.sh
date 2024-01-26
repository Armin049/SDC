#!/bin/bash

docker build -f Docker/DockerfileDebianactual -t debianactual:stable Docker

docker build -f Docker/DockerfileDev -t javalibsdcdev:latest .
