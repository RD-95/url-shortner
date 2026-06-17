# URL Shortener

A Spring Boot REST API that shortens long URLs into short, shareable links.

## Requirements
- Java 17+
- Maven 3.6+

## Run the Application

```bash
mvn spring-boot:run
```

App starts on `http://localhost:8080`

## API Usage

### Shorten a URL
**POST** `/shorten`

Request:
```json
{
  "originalUrl": "https://www.example.com/some/very/long/path?param=value"
}
```

Response:
```json
{
  "originalUrl": "https://www.example.com/some/very/long/path?param=value",
  "shortUrl": "http://localhost:8080/aB3xY7z",
  "shortCode": "aB3xY7z"
}
```

### Redirect to Original URL
**GET** `/{shortCode}`

Automatically redirects (HTTP 302) to the original URL.

## Example with curl

```bash
# Shorten a URL
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: application/json" \
  -d '{"originalUrl": "https://www.google.com/search?q=url+shortener"}'

# Use the short URL (follow redirect)
curl -L http://localhost:8080/aB3xY7z
```

## Notes
- Storage is **in-memory** — data is lost on restart
- Same URL always returns the same short code
- Short codes are 7 characters (alphanumeric)
