# Сценарий сдачи — Pan Eduard, IT2-2312
Все команды ниже — инструкции, а не утверждение о выполнении. Сохраняйте настоящий вывод и скриншоты.

## 1. Linux
В Linux из корня проекта: bash scripts/Pan_Eduard_linux_demo.sh
Сохранить лог, анализ фамилии, ls -l (744), дерево, cmp резервной копии. Git хранит только флаг executable, поэтому chmod 744 выполняется после клонирования.

## 2. GitHub
Локальные ветки и конфликт уже подготовлены; историю можно изучить через git log --all --graph --oneline.
Создать пустой репозиторий pan-eduard-student-api в своём GitHub (без README).

    git remote add origin https://github.com/YOUR_LOGIN/pan-eduard-student-api.git
    git push -u origin main
    git push origin feature/pan-eduard-crud feature/pan-eduard-documentation
    git push origin v1.0-pan-eduard
    git remote -v
    git branch -a
    git show v1.0-pan-eduard

Email локального Git взят из существующей настройки пользователя. При необходимости изменить его до новых коммитов; пароль не включать в URL.

## 3. Docker и API
Создать .env по примеру, задать пароль, выполнить docker compose up -d --build.

    docker compose ps
    docker image inspect pan-eduard-student-api:v1.0-pan-eduard --format '{{.Created}} {{.Size}}'
    docker compose logs app
    docker inspect "$(docker compose ps -q app)"
    docker compose exec app java -version
    docker compose stop app
    docker compose start app

Дождаться HTTP 200 на /actuator/health. Далее Bash/curl:

    curl -i -H 'Content-Type: application/json' -d '{"name":"Pan Eduard","groupName":"IT2-2312","email":"pan@example.com"}' http://localhost:8080/api/students
    curl -i http://localhost:8080/api/students

Вставить фактический ID из ответа вместо ID ниже:

    curl -i http://localhost:8080/api/students/ID
    curl -i -X PUT -H 'Content-Type: application/json' -d '{"name":"Pan Eduard","groupName":"IT2-2312-updated","email":"pan@example.com"}' http://localhost:8080/api/students/ID
    docker compose down
    docker compose up -d
    curl -i http://localhost:8080/api/students/ID
    curl -i -X DELETE http://localhost:8080/api/students/ID
    curl -i http://localhost:8080/api/students/ID

После down/up запись должна сохраниться; дождаться готовности перед GET. Не использовать down -v: он удаляет данные.
Последний GET должен вернуть 404. Сохранить все статусы и JSON в evidence.
Для пересборки: изменить версию в build.gradle и ответе GET /, изменить image tag в Compose и Jenkinsfile на v1.1-pan-eduard, затем docker compose up -d --build; проверить GET / и image inspect. Сделать отдельный коммит.

## 4. Jenkins
Создать job Pan_Eduard_Student_API согласно README. Запустить вручную, сохранить Console Output, результаты JUnit и build history.
Для контролируемого сбоя создать отдельную ветку:

    git switch -c feature/pan-eduard-ci-failure

В тесте lifecycle заменить ожидаемое имя Pan Eduard на INTENTIONAL FAILURE; commit и push. Создать отдельную тестовую Pipeline job для этой ветки. Запустить: Test должен упасть, Docker Build и Verify должны быть пропущены.
Вернуть ожидаемое имя, commit и push; получить успешный запуск. Не переносить ошибку в main.
Для автоматического триггера: после первого запуска изменить документацию, commit и push; дождаться polling (до 5 минут), сохранить причину запуска SCM change и историю.
Jenkins Credentials нужны для приватного checkout; pipeline не публикует образ и не выполняет deployment.

## Что сдавать
Исходники, ссылку GitHub, git history/tag, Linux log/permissions/backup, Docker inspect/ps/logs, HTTP ответы, доказательство persistence, Jenkins success/failure/fixed/automatic trigger.
Не выдавать инструкции и пустые файлы за evidence. До выполнения внешних пунктов сдача по всем требованиям не завершена.
