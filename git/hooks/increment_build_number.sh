#!/bin/bash
# copy this file to /PROJECT_DIR/.git/hooks
buildNumber=$(($(cat build_number.txt)+1))
echo $buildNumber > build_number.txt