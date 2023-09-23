#!/bin/bash
javac -d ./out ./src/main/java/org/example/Main.java  ./src/main/java/org/example/HeapSort.java
javadoc -d ./javadoc ./src/main/java/org/example/Main.java  ./src/main/java/org/example/HeapSort.java
cd ./out/
java -classpath . org.example.Main
read -p "Press enter to continue"