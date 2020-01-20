#!/bin/bash

export PATH_TO_FX=./lib/javafx-sdk-13.0.2/lib

./rmclass.sh

javac --module-path $PATH_TO_FX --add-modules javafx.controls --add-modules javafx.fxml,javafx.base Project/Project.java

java --module-path $PATH_TO_FX --add-modules javafx.controls --add-modules javafx.fxml,javafx.base Project.Project
