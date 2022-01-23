#!/bin/bash


while ! curl rabbitmq:15672; do
  sleep 7
done

java -cp app:app/lib/* Project.TwitterBot.TwitterBotApp cats dogs Cloud9 Lakers
