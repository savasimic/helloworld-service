
#!/bin/sh

ENV_NAME=$1
CONFIG_FILE=application.yaml
if [[ "$ENV_NAME" ]]; then
  CONFIG_FILE=application-$ENV_NAME.yaml
fi

java -jar `ls -t target/helloworld-service-*-SNAPSHOT.jar | head -n 1` server src/main/resources/$CONFIG_FILE
