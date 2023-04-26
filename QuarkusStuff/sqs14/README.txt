1 docker-compose
2
export QUEUE_URL=`aws sqs create-queue --queue-name=ColliderQueue --profile localstack --endpoint-url=http://localhost:8010 | jq -r .QueueUrl`
3 mvn quarkus:dev -Dqueue.url=${QUEUE_URL}
