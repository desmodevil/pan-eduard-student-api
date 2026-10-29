# Pan Eduard Student API
Student: Pan Eduard | Group: IT2-2312
Documentation status: submission guide and defense notes

Java 17+, Spring Boot 3.5.7, Gradle wrapper 8.14.3, PostgreSQL 17.
Build: ./gradlew clean test bootJar (Windows: gradlew.bat).
Tests use H2; actual PostgreSQL requires Docker integration checks.

## Запуск
1. Запустить Docker engine.
2. cp .env.example .env — заменить пароль в .env.
3. docker compose up -d --build
4. docker compose ps; docker compose logs app
5. curl http://localhost:8080/actuator/health

Адрес проекта pan-eduard-student-api: http://localhost:8080; внутри сети http://app:8080.
POST/GET /api/students; GET/PUT/DELETE /api/students/{id}.
JSON: {"name":"Pan Eduard","groupName":"IT2-2312","email":"pan@example.com"}.
POST: 201 + Location; DELETE: 204; отсутствующий ID: 404; невалидные данные: 400.
GET / отображает имя, группу и версию.

## Linux
bash scripts/Pan_Eduard_linux_demo.sh
Нужны Linux, bash, procps, coreutils, util-linux. chmod 744: rwx/r--/r--.
Пустой исходный лог не является evidence. > перезаписывает; >> дописывает.

## Jenkins
Создать Pipeline from SCM Pan_Eduard_Student_API, свой GitHub URL, main, Jenkinsfile.
Linux agent label docker-java17: JDK 17, Git, Docker CLI и daemon. Wrapper скачивает Gradle.
Плагины Pipeline, Git, JUnit; credentials приватного GitHub хранить в Jenkins Credentials.
Polling H/5 активируется после первого ручного запуска и реагирует на новые коммиты.
Сценарий сдачи: docs/DEMO.md. Защита: docs/DEFENSE.md. Проверки: evidence/VERIFICATION.md.

Учебный сервис без авторизации, доступ с хоста привязан к loopback. ddl-auto=update — для лабораторной; production требует миграций.
.env исключён из Git: удаление пароля из файла не удаляет его из истории.
Совместимость: https://docs.spring.io/spring-boot/3.5/system-requirements.html

Feature Pan Eduard: GET /api/students returns records ordered by ID for reproducible demonstrations.


