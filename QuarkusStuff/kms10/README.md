1 exec docker-compose
2. export MASTER_KEY_ARN=`aws kms create-key --profile localstack --endpoint-url=http://localhost:8011 | jq -r '.KeyMetadata.KeyId'
3. aws kms generate-data-key --key-id ${MASTER_KEY_ARN} --key-spec AES_256 --profile localstack --endpoint-url=http://localhost:8011

4. mvn quarkus:dev -Dkey.arn=${MASTER_KEY_ARN}
