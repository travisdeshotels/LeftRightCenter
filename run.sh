#!/usr/bin/env bash
# set LOCAL_DIR to the directory where you want the output file
docker build -t lcrimage .
docker run -v "${LOCAL_DIR}":/opt/lcr-out --name lcr lcrimage
