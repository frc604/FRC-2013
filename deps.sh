#!/bin/bash

set -e

if [[ "$1" == "robotnik-framework" ]]
then
  if [[ "$2" == "install" ]]
  then
    git remote add -f robotnik-framework git@github.com:frc604/robotnik-framework.git
  elif [[ "$2" == "update" ]]
  then
    git fetch robotnik-framework
    git rm -r src/com/_604robotics/robotnik/ > /dev/null
    git read-tree --prefix=src/com/_604robotics/robotnik/ -u robotnik-framework/master
    git add .

    echo
    git status
    echo

    echo "Press <Enter> to commit, or <CTRL+C> to cancel"
    read

    if [[ "$3" == "secure" ]]
    then
      git commit -m "Merge from frc604/robotnik-framework"
    else
      git commit -S -m "Merge from frc604/robotnik-framework"
    fi
  else
    echo "Please specify an action to perform:"
    echo "    install    update"
  fi
else
  echo "Please specify a dependency to merge:"
  echo "    robotnik-framework"
fi
