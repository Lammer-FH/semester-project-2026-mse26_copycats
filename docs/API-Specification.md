# Hotel Technikum – API Specification

## Endpoint Overview

| Method | Path                       | Description                      | Auth Required |
|--------|----------------------------|----------------------------------|---------------|
| GET    | /api/v1/room-types         | List all room types (paginated)  | No            |
| GET    | /api/v1/room-types/{id}    | Get room type details            | No            |
| POST   | /bookings                  | Create a booking                 | No            |
| GET    | /bookings/{id}             | Get booking details              | No            |
| POST   | /guests                    | Register a new guest             | No            |
| POST   | /auth/login                | Authenticate a guest             | No            |
| GET    | /guests/{id}/bookings      | Get all bookings of a guest      | Yes           |

---

## Room Types

### GET /api/v1/room-types

Returns a paginated list of all room types including their extras.

**Query Parameters**

| Parameter | Type    | Default | Description                    |
|-----------|---------|---------|--------------------------------|
| page      | integer | 0       | Page index (0-based)           |
| size      | integer | 5       | Number of room types per page  |

**Response `200 OK`**

```json
{
  "content": [
    {
      "id": 1,
      "name": "Deluxe Suite",
      "description": "A spacious suite with a view of the city.",
      "imagePath": "/images/rooms/1.jpg",
      "extras": [
        { "name": "WiFi", "iconPath": "/images/extras/wifi.svg" },
        { "name": "Breakfast", "iconPath": "/images/extras/breakfast.svg" }
      ]
    }
  ],
  "page": 0,
  "size": 5,
  "totalElements": 12,
  "totalPages": 3
}
```

---

### GET /api/v1/room-types/{id}

Returns the full details of a single room type including all extras.

**Path Parameters**

| Parameter | Type    | Description |
|-----------|---------|-------------|
| id        | integer | Room type ID |

**Response `200 OK`**

```json
{
  "id": 1,
  "name": "Deluxe Suite",
  "description": "A spacious suite with a view of the city.",
  "imagePath": "/images/rooms/1.jpg",
  "extras": [
    { "name": "WiFi", "iconPath": "/images/extras/wifi.svg" },
    { "name": "Breakfast", "iconPath": "/images/extras/breakfast.svg" }
  ]
}
```

**Response `404 Not Found`**

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Room with id 99 not found"
}
```

---

### GET /rooms/{id}/availability

Checks whether a room is available for a given period.

**Path Parameters**

| Parameter | Type    | Description |
|-----------|---------|-------------|
| id        | integer | Room ID     |

**Query Parameters**

| Parameter | Type              | Required | Description                     |
|-----------|-------------------|----------|---------------------------------|
| checkIn   | date (YYYY-MM-DD) | Yes      | Requested check-in date         |
| checkOut  | date (YYYY-MM-DD) | Yes      | Requested check-out date        |

**Response `200 OK`**

```json
{
  "roomId": 1,
  "checkIn": "2026-07-01",
  "checkOut": "2026-07-05",
  "available": true
}
```

**Response `400 Bad Request`** (invalid or missing parameters)

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "checkOut must be after checkIn"
}
```

**Response `404 Not Found`**

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Room with id 99 not found"
}
```

---

## Bookings

### POST /bookings

Creates a new booking. Can be submitted by an unauthenticated guest or a registered guest (`guestId` optional).
`confirmEmail` is validated server-side but not stored.

**Request Body**

```json
{
  "roomTypeId": 1,
  "checkIn": "2026-07-01",
  "checkOut": "2026-07-05",
  "firstName": "Max",
  "lastName": "Mustermann",
  "email": "max@example.com",
  "confirmEmail": "max@example.com",
  "breakfast": true,
  "guestId": null
}
```

| Field        | Type    | Required | Description                                        |
|--------------|---------|----------|----------------------------------------------------|
| roomTypeId   | integer | Yes      | ID of the room type to book                        |
| checkIn      | date    | Yes      | Check-in date (YYYY-MM-DD)                         |
| checkOut     | date    | Yes      | Check-out date (YYYY-MM-DD), must be after checkIn |
| firstName    | string  | Yes      | Guest first name                                   |
| lastName     | string  | Yes      | Guest last name                                    |
| email        | string  | Yes      | Valid guest email address                          |
| confirmEmail | string  | Yes      | Must match `email` exactly                         |
| breakfast    | boolean | Yes      | Whether breakfast is included                      |
| guestId      | integer | No       | Registered guest ID to link the booking to         |

**Response `201 Created`**

```json
{
  "id": 42,
  "roomType": {
    "id": 1,
    "name": "Deluxe Suite",
    "description": "A spacious suite with a view of the city.",
    "imagePath": "/images/rooms/1.jpg",
    "extras": [
      { "name": "WiFi", "iconPath": "/images/extras/wifi.svg" }
    ]
  },
  "checkIn": "2026-07-01",
  "checkOut": "2026-07-05",
  "firstName": "Max",
  "lastName": "Mustermann",
  "email": "max@example.com",
  "breakfast": true,
  "status": "CONFIRMED",
  "createdAt": "2026-05-04T10:00:00Z"
}
```

**Response `400 Bad Request`**

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Email addresses do not match"
}
```

**Response `409 Conflict`** (room unavailable for the requested period)

```json
{
  "status": 409,
  "error": "Conflict",
  "message": "No room of type 1 is available from 2026-07-01 to 2026-07-05"
}
```

---

### GET /bookings/{id}

Returns the full details of a booking. Used for the booking confirmation page (U5).

**Path Parameters**

| Parameter | Type    | Description |
|-----------|---------|-------------|
| id        | integer | Booking ID  |

**Response `200 OK`**

Same structure as the `POST /bookings` response body.

**Response `404 Not Found`**

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Booking with id 42 not found"
}
```

---

## Guests

### POST /guests

Registers a new guest account.

**Request Body**

```json
{
  "firstName": "Max",
  "lastName": "Mustermann",
  "email": "max@example.com",
  "password": "securePassword123"
}
```

| Field     | Type   | Required | Description                               |
|-----------|--------|----------|-------------------------------------------|
| firstName | string | Yes      | First name                                |
| lastName  | string | Yes      | Last name                                 |
| email     | string | Yes      | Unique email address                      |
| password  | string | Yes      | Plaintext password (hashed server-side)   |

**Response `201 Created`**

```json
{
  "id": 7,
  "firstName": "Max",
  "lastName": "Mustermann",
  "email": "max@example.com"
}
```

> Note: `password_hash` is never returned in any response.

**Response `409 Conflict`** (email already registered)

```json
{
  "status": 409,
  "error": "Conflict",
  "message": "Email max@example.com is already registered"
}
```

---

### POST /auth/login

Authenticates a guest and returns a JWT for subsequent authenticated requests.

**Request Body**

```json
{
  "email": "max@example.com",
  "password": "securePassword123"
}
```

**Response `200 OK`**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "guestId": 7,
  "firstName": "Max",
  "lastName": "Mustermann",
  "email": "max@example.com"
}
```

**Response `401 Unauthorized`**

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password"
}
```

---

### GET /guests/{id}/bookings

Returns all bookings belonging to a registered guest. Requires authentication.

**Path Parameters**

| Parameter | Type    | Description |
|-----------|---------|-------------|
| id        | integer | Guest ID    |

**Headers**

| Header        | Value            |
|---------------|------------------|
| Authorization | Bearer {token}   |

**Response `200 OK`**

```json
[
  {
    "id": 42,
    "roomType": {
      "id": 1,
      "name": "Deluxe Suite",
      "description": "A spacious suite with a view of the city.",
      "imagePath": "/images/rooms/1.jpg",
      "extras": [
        { "name": "WiFi", "iconPath": "/images/extras/wifi.svg" }
      ]
    },
    "checkIn": "2026-07-01",
    "checkOut": "2026-07-05",
    "firstName": "Max",
    "lastName": "Mustermann",
    "email": "max@example.com",
    "breakfast": true,
    "status": "CONFIRMED",
    "createdAt": "2026-05-04T10:00:00Z"
  }
]
```

**Response `401 Unauthorized`** (no or invalid token)

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Authentication required"
}
```

**Response `403 Forbidden`** (token belongs to a different guest)

```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied"
}
```

---

## Error Response Format

All error responses follow a consistent structure:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Human-readable description of the error"
}
```
