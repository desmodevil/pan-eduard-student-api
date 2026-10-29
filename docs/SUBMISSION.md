# Сдача и перенос — Pan Eduard / IT2-2312

## Что показать преподавателю
1. Репозиторий GitHub и README. git log --all --graph --oneline; git branch -a; git tag. Объяснить две ветки CRUD/documentation, конфликт и разрешение.
2. В Ubuntu открыть scripts/Pan_Eduard_system_report.sh; ls -l (после chmod 744); показать logs и backups; объяснить > и >>.
3. sudo docker compose ps: app на 8081, PostgreSQL healthy. GET http://localhost:8081/ показывает имя, группу и 1.1-pan-eduard. Показать сохранённые CRUD ответы и отличие 404 от 500.
4. Dockerfile: JDK build, JRE runtime, non-root 10001. Compose: db как DNS-имя, named volume, network, healthcheck. Версия 1.1 подтверждает пересборку.
5. Jenkins http://localhost:8080 ВНУТРИ Ubuntu: основной job Pan_Eduard_Student_API и тестовый Pan_Eduard_Student_API_Failure. Показать SUCCESS, SCM trigger, FAIL на тесте и последующий SUCCESS. Не выдавать старую синтаксическую ошибку за контролируемый тестовый сбой.
6. Ответы для защиты — DEFENSE.md. Автоматического deployment нет: это CI pipeline с проверкой Docker image.

## Запуск после перезагрузки
В терминале Ubuntu:

    cd ~/pan-eduard-midterm/pan-eduard-student-api
    sudo docker compose up -d
    sudo systemctl start jenkins
    sudo docker compose ps
    curl http://localhost:8081/actuator/health

Дождаться готовности приложения. Порт 8080 занят Jenkins, внешний порт API — 8081; внутри контейнера API слушает 8080.
На учебной машине docker-команды пользователя требуют sudo; Jenkins уже имеет доступ к daemon.

## Перенос на ноутбук Windows/Intel
Дождаться завершения сборок. Полностью выключить Ubuntu через Shut Down. В VirtualBox экспортировать Ubuntu Devops в Pan_Eduard_DevOps.ova. На ноутбуке установить совместимую версию VirtualBox, включить виртуализацию, импортировать OVA. Предусмотреть место под архив и распакованную VM; текущей VM выделено 5 ГБ RAM.
Экспорт сохраняет текущее содержимое виртуального диска (проект, Docker, PostgreSQL volume, Jenkins), но не дерево snapshots и не файлы внешних общих папок. Запустить и проверить обе страницы в браузере Ubuntu заранее. Docker Desktop на ноутбуке не нужен.
Не передавать OVA публично: виртуальный диск содержит локальные пароли и настройки Jenkins. Преподавателю исходники и отчёт передаются отдельно.

## Перед сдачей
- Для точного соответствия переименовать GitHub repo в pan-eduard-student-api и обновить origin и оба Jenkins SCM URL.
- Дождаться успешной Jenkins-сборки версии 1.1 и сохранить Console Output.
- Сохранить скриншоты build history, тестов и Docker ps при необходимости формата сдачи.
- Убедиться, что архив и GitHub содержат актуальный VERIFICATION.md и четыре Jenkins журнала.
