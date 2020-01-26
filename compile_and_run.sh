#!/bin/bash

export PATH_TO_FX=./lib/javafx-sdk-13.0.2/lib

export PATH_TO_PSQL_DRIVER=./lib/postgresql-42.2.9
./rmclass.sh

javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml,javafx.base Project/Project.java

if [ $? == 0 ]; then
	echo "Compilation successfull"
	java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml,javafx.base -cp .:./lib/postgresql-42.2.9/postgresql-42.2.9.jar Project/Project.java
fi
