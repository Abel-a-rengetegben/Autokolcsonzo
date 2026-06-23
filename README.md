# Autókölcsönző alkalmazás

Spring Boot alapú autókölcsönző webalkalmazás.

## Funkciók

- Autók keresése dátum alapján
- Autófoglalás
- Admin felület
    - Foglalások megtekintése
    - Autók létrehozása
    - Autók szerkesztése
    - Autók deaktiválása
    - Képfeltöltés
- REST API
    - Elérhető autók lekérdezése
    - Foglalás létrehozása

## Technológiák

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- JUnit 5
- Mockito

## Indítás

1. PostgreSQL adatbázis létrehozása:
    - adatbázis neve: `autokolcsonzo`

2. Az `application.properties` beállítása.

3. Alkalmazás indítása:

```bash
mvn spring-boot:run
```

4. Böngésző:

```text
http://localhost:8080
```

## Admin felület

```text
http://localhost:8080/admin
```