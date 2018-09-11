#!/bin/bash

# Create a single jar file
mvn clean compile assembly:single


# Run the main program 
java -cp target/*.jar twenty_day_hero.Main

