# keycloak-introspect-endpoint

![java](https://img.shields.io/badge/java-18-blue)
![keycloak](https://img.shields.io/badge/keycloak-21.0.0-blue)

## Run keycloak
```
cd .docker
docker-compose -f docker-compose.yaml up -d
```

## Run Project
```
mvn clean spring-boot:run
```

## Generate token
```
curl --location --request POST 'http://localhost:8083/realms/dev/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=postman' \
--data-urlencode 'client_secret=7rCrlhM36wB3KwuLWGjRxZalEVtTVtrJ'
```

## Introspect
```
curl --location --request POST 'http://localhost:8081/api/v1/token/introspect' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'token=eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJZcFJGWFRFU0lXNTh5QjJfd1VOekJGUW13Y3lmY0U2aXZQYVlxTC1sT3FJIn0.eyJleHAiOjE2ODU0NTE1NTMsImlhdCI6MTY4NTQ1MTI1MywianRpIjoiYTQzN2E3YjMtZTU1My00YjliLWIyZWEtODdiZGFjNDQxN2Q4IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgzL3JlYWxtcy9kZXYiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiODI3YjlhNTUtMDI1OS00ODU4LWFhMDctZDM5MjAwODFhMzk3IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicG9zdG1hbiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiLyoiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtZGV2Iiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY2xpZW50SWQiOiJwb3N0bWFuIiwiY2xpZW50SG9zdCI6IjE3Mi4xOC4wLjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtcG9zdG1hbiIsImNsaWVudEFkZHJlc3MiOiIxNzIuMTguMC4xIn0.GvXERylrfX3WrD_U5mdQc-FKWL_Maqx_cfReTWatnzo0vAn3qCDD_nWnYn8qdQHQK91AepWG9jolgZxDohTYZObobasaemSbo_cY5GDBszHeAwB97aL-dFKPUTNNPjznLwzvE1e9GCaDYIsdCarmkOIf4_1TqMpgKaMwfGfAlp2i_Y16V0H36vq-EbO2SglsTIEU7n08eZ5VN_GcbXJEAzPJf3-N1by1UbSQvur3GuY5k-M7ugdXfEfE_rx2I4oEZ0WtIE-LsSeP_Negtb6IINSKFfvVwnij9PgBF3DjLA9zr8SUV4JHpTHXDm8N7QN9gVYA_d_RILimXojKA-Vf6g' \
--data-urlencode 'token_type_hint=token'
```