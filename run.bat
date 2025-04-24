@echo off
javac -d out src/integration/*.java
java -cp out src.integration.Main
pause