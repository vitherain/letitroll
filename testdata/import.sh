#!/bin/bash

# Projects
mongoimport -h ds121299.mlab.com:21299 -d letitroll -c projects -u admin -p YeJqDDmHR7Hp --file projects.json --jsonArray
# Environments
mongoimport -h ds121299.mlab.com:21299 -d letitroll -c environments -u admin -p YeJqDDmHR7Hp --file environments.json --jsonArray
# Users
mongoimport -h ds121299.mlab.com:21299 -d letitroll -c users -u admin -p YeJqDDmHR7Hp --file users.json --jsonArray
# Features
mongoimport -h ds121299.mlab.com:21299 -d letitroll -c features -u admin -p YeJqDDmHR7Hp --file features.json --jsonArray
# Feature targetings
mongoimport -h ds121299.mlab.com:21299 -d letitroll -c targetings -u admin -p YeJqDDmHR7Hp --file feature-targetings.json --jsonArray