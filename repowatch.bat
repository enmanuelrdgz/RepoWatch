@echo off
mvn -q exec:java -Dexec.mainClass="org.example.Main" -Dexec.args="%*"