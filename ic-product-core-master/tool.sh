#!/usr/bin/env bash

case $1 in
deploy)
  mvn clean deploy -Dmaven.test.skip -pl ic-base,ic-category/ic-category-api,ic-item/ic-item-api -am
  ;;
*)
  echo "tool.sh [COMMANDS]"
esac
