# Spring Security demo (Java 21)

Минимальный проект на **Java 21 + Spring Boot + Spring Security**, где можно:
- авторизоваться через форму логина;
- просматривать свои роли;
- (для ADMIN) менять роли пользователей через UI и REST.

## Стек
- Spring Boot 3.3.x
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 (in-memory)

## Запуск
```bash
./mvnw spring-boot:run
```
или
```bash
mvn spring-boot:run
```

## Тестовые пользователи
Создаются при старте:
- `admin` / `admin123` (роли: `ROLE_ADMIN`, `ROLE_USER`)
- `user` / `user123` (роль: `ROLE_USER`)

## Полезные URL
- Логин: `http://localhost:8080/login`
- Главная: `http://localhost:8080/`
- Управление ролями (ADMIN): `http://localhost:8080/admin/users`
- H2 console: `http://localhost:8080/h2-console`

## REST для смены ролей
Только для `ROLE_ADMIN`:

```http
PATCH /admin/users/{username}/roles
Content-Type: application/json

{
  "roles": ["ROLE_USER", "ROLE_ADMIN"]
}
```
