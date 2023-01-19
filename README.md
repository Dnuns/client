# Client API

This is a simple REST API CRUD.

## Tech Stack

**Server:** Java 17, Spring, Postegres, JUnit

## API Reference

#### Get clients

```http
  GET http://localhost:8080/clients
```

#### Get client by id

```http
  GET http://localhost:8080/clients/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of the client to fetch |

#### Add client

```http
  POST http://localhost:8080/clients
```

```
{
  "name": "Maria Silva",
  "cni": "19830212F002U",
  "amount": "2000",
  "birthDate": "1982-10-01T10:00:00Z"
}
```

#### Update client
```http
  PUT http://localhost:8080/clients/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of client to update |

```
{
  "name": "Maria Silva De Pina",
  "cni": "19830212F002U",
  "amount": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}
```
#### Delete client
```http
  DELETE http://localhost:8080/clients/${id}
```

## Author

- [David Nunes](https://www.github.com/Dnuns)
