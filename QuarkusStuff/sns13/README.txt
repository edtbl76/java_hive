1 docker-compose
2 export TOPIC_ARN=`aws sns create-topic --name=QuarksCollider --profile localstack --endpoint-url=http://localhost:8009 | jq -r .TopicArn`
3 mvn quarkus:dev -Dtopic.arn=${TOPIC_ARN}
