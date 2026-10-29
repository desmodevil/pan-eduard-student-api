# Защита — Pan Eduard, IT2-2312
## Рассказ на 2 минуты
Я подготовил REST CRUD для студентов на Spring Boot и Gradle. Контроллер валидирует имя, группу и email; JPA сохраняет записи в PostgreSQL. POST возвращает 201, DELETE 204, отсутствующий ID 404. Тесты проверяют полный CRUD, неверные данные и отсутствие записи. Они используют H2; PostgreSQL проверяется отдельно в Compose.
В multi-stage Dockerfile JDK собирает JAR, JRE запускает его от непривилегированного пользователя. Compose связывает приложение и БД, ждёт readiness PostgreSQL и хранит данные в named volume. Jenkins выполняет checkout, build, test, docker build, verify. При ошибке команда sh останавливает pipeline.
В Git есть персональные ветки, meaningful commits, настоящий merge conflict README и разрешение с сохранением обоих смыслов. Что реально запускалось — отдельно в evidence/VERIFICATION.md.

## Короткие ответы
- Linux — ОС; процессы имеют PID. ps aux показывает процессы, pgrep -af java ищет Java, kill PID посылает SIGTERM; сначала проверить PID.
- pwd — текущая папка; абсолютный путь начинается от корня, относительный — от текущей папки.
- mkdir создаёт папку, mkdir -p также родителей. touch создаёт пустой файл, ls -la показывает скрытые файлы. cp копирует, mv перемещает.
- #!/bin/bash выбирает интерпретатор; аргументы — $1, $2, все — "$@".
- chmod +x добавляет выполнение; 744 = rwx/r--/r--; 755 = rwx/r-x/r-x.
- free -h — RAM/swap; df -h — место дисков; tail -n 5 — последние строки; grep Pan file > result — поиск с перезаписью результата; >> дописывает.
- Git — локальная VCS, GitHub — хостинг. init создаёт репозиторий, add переносит изменения в index, commit сохраняет снимок index. status показывает staged/unstaged/untracked.
- Working tree — файлы, staging/index — следующий снимок, repository — история. Ветка — перемещаемый указатель на commit; tag — обозначение версии.
- switch -c создаёт ветку; merge объединяет, rebase переносит коммиты на другую базу и меняет их hash. Не rebase общую историю без согласования.
- fetch получает удалённую историю, pull делает fetch и интеграцию. push отправляет коммиты. При расхождении: status, fetch, log --all --graph, diff, затем осознанный merge/rebase; не force push вслепую.
- Конфликт — несовместимые изменения одинаковых строк. Проверить обе стороны, отредактировать итог, удалить маркеры, git add, commit, проверить diff.
- .gitignore исключает незакоммиченные файлы; не убирает секрет из истории. Секрет после утечки нужно отозвать.
- Docker image — шаблон слоёв, container — экземпляр процесса. build собирает image, run запускает. docker run -p 8080:8080 IMAGE публикует порт.
- FROM задаёт базу; WORKDIR рабочую папку; RUN выполняется при build; CMD задаёт команду по умолчанию запуска; ENTRYPOINT задаёт исполняемую команду; EXPOSE документирует порт, не публикует его.
- COPY копирует, ADD дополнительно умеет архивы/URL; здесь достаточно COPY. Multi-stage оставляет компилятор и исходники за пределами runtime.
- Compose описывает несколько сервисов. Network связывает контейнеры, volume сохраняет данные. localhost внутри app — сам app; db разрешается Docker DNS в контейнер PostgreSQL.
- Healthcheck проверяет готовность БД; running ещё не означает ready. docker logs/compose logs показывает stdout/stderr, docker exec/compose exec запускает команду внутри.
- docker stop останавливает, docker rm удаляет контейнер; volume живёт отдельно. Пароль передаётся через environment из локального .env, для production — secret manager.
- Jenkinsfile — versioned pipeline. Job — настройка задания; controller планирует, agent исполняет. Для docker build агенту нужен CLI и доступ к daemon.
- Checkout получает код; Build компилирует; Test ищет регрессии; Verify проверяет образ. Credentials хранят токены вне репозитория.
- Declarative pipeline задаёт структуру pipeline/stages/steps; Scripted использует Groovy flow. Console Output содержит команды, ошибки, этапы; JUnit — результаты тестов.
- CI — частая интеграция со сборкой и тестами. Continuous Delivery — готовность релиза с ручным решением; Continuous Deployment — автоматическая доставка в production. В этом задании deployment не нужен.
- Trigger запускает сборку: webhook сообщает о событии, polling периодически проверяет SCM. Ошибка sh останавливает последующие стадии; не скрывать её через || true.

## Разбор проблем
БД недоступна: compose ps, logs db/app, healthcheck, DB_URL с db:5432, общая сеть, переменные и пароль, состояние volume. Изменение POSTGRES_PASSWORD не меняет пароль уже инициализированной БД.
Docker Build в Jenkins падает: проверить точную ошибку, docker info на агенте, права daemon, registry/network, Dockerfile и Gradle вывод. Не исправлять права глобальным chmod 777.
Gradle падает: посмотреть первый root cause, java -version, ./gradlew --version, затем ./gradlew test --stacktrace и JUnit XML. Отличать сетевой сбой скачивания от failed assertion.
