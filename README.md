# Coffee Rest Api

This REST Api reveals endpoints to operate coffee machines. It can create an order, get status of a coffee machine and list all coffee machines.

## POST example
```bash
curl -X POST \
  http://localhost:8080/coffeemachines/ \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ab7c372d-91e4-8544-9128-6dceddcea6c1' \
  -d '{
	"coffeeMachineId": 1,
	"coffeeType": "LATTE"
}'
```

## GET example
```bash
curl -X GET \
  http://localhost:8080/coffeemachines/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: e2d4f995-472b-c6fd-5b67-8912a36d8143'
```

```bash
curl -X GET \
  http://localhost:8080/coffeemachines/ \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 0eb37f68-3649-209a-6c53-2110eb93c8eb'
```
 
