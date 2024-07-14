# Book Management REST API

Dieses Projekt ist eine Spring Boot REST API, die eine Bibliothek verwaltet, indem Bücher und Autoren in einer MongoDB-Datenbank gespeichert werden. Die API ermöglicht das Erstellen, Aktualisieren, Abrufen und Löschen von Büchern und Autoren sowie das Abrufen von Büchern nach Autor.

## Voraussetzungen

- Java 11 oder höher
- Gradle
- MongoDB


## Installation und Start

1. **Klonen Sie das Repository:**

    ```bash
    git clone https://github.com/JGlumanda/Web-Engineering-2-Labor.git
    cd Web-Engineering-2-Labor
    ```

2. **MongoDB starten:**

    Stellen Sie sicher, dass MongoDB auf Ihrem lokalen Rechner läuft. Standardmäßig verwendet die Anwendung die MongoDB-Instanz unter `localhost:27017`. Sie können dies in der Datei `application.properties` anpassen.

3. **Abhängigkeiten installieren und das Projekt starten:**

    ```bash
    ./gradlew bootRun
    ```

    Dies wird die Anwendung auf `http://localhost:8080` starten.

## Endpunkte

### Autoren

- **Erstellen eines Autors:**

    ```
    POST /api/authors
    ```

    Beispiel-Request-Body:
    ```json
    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "address": {
            "street": "123 Main St",
            "postalCode": "12345",
            "city": "Anytown"
        }
    }
    ```

- **Abrufen aller Autoren:**

    ```
    GET /api/authors
    ```

- **Abrufen eines Autors nach ID:**

    ```
    GET /api/authors/{id}
    ```

- **Aktualisieren eines Autors:**

    ```
    PUT /api/authors/{id}
    ```

    Beispiel-Request-Body:
    ```json
    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "address": {
            "street": "123 Main St",
            "postalCode": "12345",
            "city": "Anytown"
        }
    }
    ```

- **Löschen eines Autors:**

    ```
    DELETE /api/authors/{id}
    ```

- **Abrufen aller Bücher eines Autors:**

    ```
    GET /api/authors/{id}/books
    ```

### Bücher

- **Erstellen eines Buches:**

    ```
    POST /api/books
    ```

    Beispiel-Request-Body:
    ```json
    {
        "isbn": "1234567890123",
        "title": "Example Book",
        "authorIds": ["authorId1", "authorId2"],
        "publisher": "Example Publisher",
        "genre": "Fiction",
        "publishedDate": "2024-01-01",
        "language": "English",
        "description": "This is an example book.",
        "pages": 300
    }
    ```

- **Abrufen aller Bücher:**

    ```
    GET /api/books
    ```

- **Abrufen eines Buches nach ID:**

    ```
    GET /api/books/{id}
    ```

- **Aktualisieren eines Buches:**

    ```
    PUT /api/books/{id}
    ```

    Beispiel-Request-Body:
    ```json
    {
        "isbn": "1234567890123",
        "title": "Updated Example Book",
        "authorIds": ["authorId1", "authorId2"],
        "publisher": "Updated Publisher",
        "genre": "Non-Fiction",
        "publishedDate": "2024-01-02",
        "language": "English",
        "description": "This is an updated example book.",
        "pages": 350
    }
    ```

- **Löschen eines Buches:**

    ```
    DELETE /api/books/{id}
    ```

## Fehlerbehandlung

Die API verwendet einen globalen Exception-Handler, um benutzerfreundliche Fehlermeldungen zurückzugeben. Zum Beispiel wird eine `ResourceNotFoundException` ausgelöst, wenn eine angeforderte Ressource nicht gefunden wird, und eine entsprechende 404-Antwort wird an den Client zurückgegeben.

## Lizenz

Dieses Projekt ist lizenziert unter der MIT-Lizenz.

