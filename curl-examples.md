# Ejemplos de uso de la Marvel Characters API (cURL)

> Dominio de test: http://bp-se-test-cabcd9b246a5.herokuapp.com
> Usuario de ejemplo: testuser

---

## Obtener todos los personajes
```sh
curl -X GET \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters
```

## Obtener personaje por ID
```sh
curl -X GET \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/1
```

## Crear personaje (válido)
```sh
curl -X POST \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Spider-Man",
    "alterego": "Peter Parker",
    "description": "Superhéroe arácnido de Marvel",
    "powers": ["Agilidad", "Sentido arácnido", "Trepar muros"]
  }'
```

## Crear personaje (nombre duplicado, error 400)
```sh
curl -X POST \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Spider-Man",
    "alterego": "Peter Parker",
    "description": "Otro intento duplicado",
    "powers": ["Agilidad"]
  }'
```

## Crear personaje (datos inválidos, error 400)
```sh
curl -X POST \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "",
    "alterego": "",
    "description": "",
    "powers": []
  }'
```

## Obtener personaje inexistente (error 404)
```sh
curl -X GET \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/9999
```

## Actualizar personaje (válido)
```sh
curl -X PUT \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/1 \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Spider-Man",
    "alterego": "Peter Parker",
    "description": "Superhéroe arácnido de Marvel (actualizado)",
    "powers": ["Agilidad", "Sentido arácnido", "Trepar muros"]
  }'
```

## Actualizar personaje inexistente (error 404)
```sh
curl -X PUT \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/9999 \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "No existe",
    "alterego": "Nadie",
    "description": "No existe",
    "powers": ["Nada"]
  }'
```

## Eliminar personaje (válido)
```sh
curl -X DELETE \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/1
```

## Eliminar personaje inexistente (error 404)
```sh
curl -X DELETE \
  http://bp-se-test-cabcd9b246a5.herokuapp.com/testuser/api/characters/9999
```
