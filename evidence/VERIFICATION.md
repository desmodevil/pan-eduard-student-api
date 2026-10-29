# Проверка задания — Pan Eduard, IT2-2312
Итоговый отчёт обновлён 30 октября 2026, часовой пояс Asia/Qyzylorda.
Журналы Ubuntu/Jenkins содержат UTC: вечер 29 октября UTC соответствует ночи 30 октября по местному времени.

## Источники доказательств
Linux-отчёт получен из Ubuntu VirtualBox. CRUD, build, Docker и версия 1.1 получены из опубликованного пользователем репозитория. Четыре журнала Jenkins предоставлены пользователем из Console Output; они прочитаны и сохранены без редактирования.

| Требование | Результат и доказательство |
|---|---|
| Linux: системный отчёт, права, backup, процессы, поиск | Pan_Eduard_Ubuntu.txt: имя, группа, дата, ОС, CPU/RAM/disk, -rwxr--r-- (744), backup; выполнение дошло до финального grep |
| Gradle build/test | Pan_Eduard_build.txt: BUILD SUCCESSFUL; тестовая БД H2 |
| Git | Удалённые main, feature/pan-eduard-crud, feature/pan-eduard-documentation, feature/pan-eduard-ci-failure проверены при клонировании; теги v1.0-pan-eduard и v1.1-pan-eduard доступны |
| Конфликт | Pan_Eduard_git_conflict.txt и Pan_Eduard_conflict_before.txt — намеренный конфликт README; разрешение видно в Git history |
| Docker/Compose/PostgreSQL | Pan_Eduard_Docker.txt: образ, inspect, logs, java, stop/start; PostgreSQL healthy |
| CRUD | Pan_Eduard_CRUD.txt: POST 201, list/GET/PUT 200, DELETE 204, отсутствующая запись 404 |
| Persistence | Pan_Eduard_CRUD.txt содержит down/up с удалением и созданием контейнеров и последующий GET 200 с сохранённой обновлённой записью. Отдельного автоматического assertion на сохранность нет |
| Jenkins успешный | Pan_Eduard_Jenkins_success.txt: Checkout/Build/Test/Docker Build/Verify, Finished: SUCCESS, JUnit и JAR |
| Jenkins автоматический | Pan_Eduard_Jenkins_automatic.txt: Started by an SCM change, SUCCESS; Test UP-TO-DATE, повторный прогон тестов не заявляется |
| Контролируемая ошибка | Pan_Eduard_Jenkins_controlled_failure.txt: ветка feature/pan-eduard-ci-failure, 4 tests/1 failed, Docker Build и Verify пропущены |
| Исправление ошибки | Pan_Eduard_Jenkins_fixed.txt: d0f8f5b, тесты реально выполнены повторно, Finished: SUCCESS |
| Пересборка версии | Pan_Eduard_version_1.1.txt: GET / возвращает 1.1-pan-eduard; Compose использует v1.1-pan-eduard; БД healthy |

## Ограничения и незакрытые детали
- GitHub называется devops-midterm-paneduard. В задании требуется pan-eduard-student-api: для полного соответствия требуется переименование и обновление origin/URL Jenkins.
- Успешный Jenkins для версии 1.1 после коммита b172e93 ещё не подтверждён отдельным журналом. Сохранённые успешные pipeline относятся к предыдущей версии.
- Перенос OVA на ноутбук и запуск на нём ещё не проверены.
- Изначальные сетевые/Java ошибки остаются в Pan_Eduard_gradle_attempt.txt как исторический журнал; они НЕ отражают итоговую работоспособность Ubuntu.
- .env не включён в комплект; доступы GitHub/Jenkins и секреты не публикуются.

Исходный код финальной версии: b172e93, tag v1.1-pan-eduard. Дополнения документации и журналов в этом комплекте подготовлены после этого коммита и требуют отдельного commit/push.

