# Проверки — 29 октября 2026
Pan Eduard, IT2-2312.

## Реально выполнено
- Прочитано исходное DevOps midterm task.docx.
- Создан проект, персональные файлы, официальный Gradle wrapper 8.14.3.
- docker compose config --quiet: exit 0 с временным DB_PASSWORD; это только проверка конфигурации.
- bash -n для обоих персональных Bash-скриптов: exit 0 (Git Bash). Linux-команды не запускались.
- Создан локальный Git main, две персональные feature-ветки, commits, merge CRUD.
- Реально возник конфликт README; сохранены вывод Git и файл с маркерами ДО разрешения. Конфликт разрешён отдельным merge commit.
- README скопирован в backups; это Windows-копия, не доказательство Linux permissions.

## Не проверено / заблокировано
- Java build/test: попытка Gradle wrapper завершилась UnknownHostException: release-assets.githubusercontent.com. Альтернативная загрузка downloads.gradle.org также не разрешила DNS. См. Pan_Eduard_gradle_attempt.txt. Тесты написаны, но НЕ прошли запуск.
- Docker engine первоначально недоступен; штатный restart не дал подтверждения готовности. Пользователь обновляет Docker. Образ не собран, контейнеры и PostgreSQL не проверены.
- HTTP CRUD и persistence подготовлены как сценарий, ответы не сфабрикованы.
- Linux permissions 744, system report и анализ процессов требуют Linux; пустой исходный log — не evidence.
- GitHub repository/push отсутствуют; remote не установлен, доступа не предоставлено.
- Jenkins job, success/failure/fix и trigger не запускались. Jenkinsfile подготовлен для Linux agent docker-java17.

## Продолжение после обновления Docker
1. docker info
2. Создать локальный .env с паролем.
3. docker compose build (нужны доступные registry, Maven Central и Gradle download hosts).
4. Для реального запуска тестов в JDK: ./gradlew clean test bootJar; либо docker run --rm -v "$PWD:/workspace" -w /workspace eclipse-temurin:17-jdk sh -c 'chmod +x gradlew && ./gradlew clean test bootJar'.
5. docker compose up -d; выполнить docs/DEMO.md.
6. Сохранить фактические логи/JUnit/HTTP ответы, обновить этот документ.

Проект пока не может считаться полностью проверенным или готовым к сдаче всех evidence.
