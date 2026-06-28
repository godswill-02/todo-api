# Todo API - Guide pour le design mobile

Cette API permet de gérer des todos via des endpoints REST simples.

## Base URL

```text
http://localhost:8080/api/v1/todo
```

## Endpoints disponibles

### 1. Lister les todos
- Method: GET
- URL: `/api/v1/todo`

### 2. Récupérer un todo par id
- Method: GET
- URL: `/api/v1/todo/{id}`

### 3. Créer un todo
- Method: POST
- URL: `/api/v1/todo`
- Body JSON:

```json
{
  "title": "Faire les courses",
  "content": "TEXT",
  "isCompleted": false
}
```

### 4. Mettre à jour un todo
- Method: PUT
- URL: `/api/v1/todo/{id}`
- Body JSON:

```json
{
  "title": "Faire les courses",
  "content": "TEXT",
  "isCompleted": true
}
```

### 5. Supprimer un todo
- Method: DELETE
- URL: `/api/v1/todo/{id}`

## Format des réponses

Toutes les réponses sont enveloppées dans un objet JSON de ce type :

```json
{
  "status": true,
  "data": {
    "id": 1,
    "title": "Faire les courses",
    "content": "TEXT",
    "isCompleted": false,
    "createdAt": "2026-06-26T07:33:04.297815Z",
    "updatedAt": "2026-06-26T07:33:04.297815Z"
  },
  "message": "Todo retrieved successfully"
}
```

## Données à afficher dans l’interface

Pour une interface mobile simple, le designer peut se baser sur :
- titre du todo
- état de complétion (`isCompleted`)
- contenu / type (`content`)

## Exemple d’écran mobile

Une interface simple pourrait contenir :
- une liste de todos
- un bouton pour ajouter un todo
- une case à cocher pour marquer comme complété
- un bouton pour supprimer un todo

## Notes importantes

- Le serveur doit être lancé avant de tester l’API.
- En local, l’API est accessible sur le port `8080`.
- Les valeurs possibles pour `content` sont : `TEXT`, `IMAGE`, `VIDEO`, `AUDIO`.

## Lancer l’application

```bash
./mvnw spring-boot:run
```
