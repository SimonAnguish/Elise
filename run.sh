#!/bin/bash

javac Groceries.java || { echo 'Compile Groceries failed' ; exit 1; }
javac Elise.java || { echo 'Compile Elise failed' ; exit 1; }
java -classpath ".:sqlite-jdbc-3.8.11.2.jar" Elise