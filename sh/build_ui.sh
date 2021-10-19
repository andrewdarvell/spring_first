#!/bin/bash
pwd
cd ../front-src
npm i
ng build --output-path '../ui_service/src/main/resources/public' --base-href="/ui/"
