# ⚽ Football Management App - JakartaEE

**Практическая работа №1 по JakartaEE**

## О проекте
Веб-приложение для управления футбольными командами и игроками. Позволяет добавлять, редактировать и удалять команды и игроков.

## Возможности
- **Добавлять команды** (название, город, стадион)
- **Добавлять игроков** в команды (имя, фамилия, позиция)
- **Редактировать** существующие записи
- **Удалять** команды и игроков
- **Просматривать** списки в удобных таблицах

## Архитектура
- **Слой данных**: JPA Entity (Player, Team)
- **Бизнес-слой**: EJB Stateless Beans (PlayerService, TeamService)
- **Слой представления**: JSF (players.xhtml, teams.xhtml)
- **База данных**: PostgreSQL
- **Сервер приложений**: GlassFish 6.2.5

## Запуск приложения
1. Установите GlassFish 6.2.5 и PostgreSQL
2. Создайте БД: `CREATE DATABASE library_db_practic1;`
3. Настройте DataSource в GlassFish (JNDI: `jdbc/footballDS`)
4. Выполните: `asadmin deploy target/Football-App-1.0-SNAPSHOT.war`
5. Откройте: http://localhost:8080/Football-App-1.0-SNAPSHOT/teams.xhtml

## Скриншоты
**Вот как это работает:**
###** 1. Проект задеплоен в GlassFish**
<img width="1852" height="740" alt="image" src="https://github.com/user-attachments/assets/6cac2c4f-758e-4eff-973e-47f3d27b4678" />
###** 2. Работающее приложение - Команды**
<img width="1850" height="783" alt="image" src="https://github.com/user-attachments/assets/3c85fe45-ab75-4ba0-a8f7-31686ec451e4" />
**Добаим Локомотив**
<img width="1859" height="824" alt="image" src="https://github.com/user-attachments/assets/f31c2692-fda0-4b56-95a6-f91e12a4c9ca" />
**Удалим Волгу нажав на крестик**
<img width="1844" height="788" alt="image" src="https://github.com/user-attachments/assets/a75a2fb5-4ed9-4006-8b97-5e0d092cb7e8" />
**Отредактируем у команды Ахмат стадион**
<img width="1826" height="768" alt="image" src="https://github.com/user-attachments/assets/b4001b1e-89b3-4acc-8d41-f8230d5c89f6" />
###** 3. Работающее приложение - Игроки**
**Переходи к управлению игроками**
<img width="1837" height="913" alt="image" src="https://github.com/user-attachments/assets/c8bf6e05-9e2f-4c1e-8ec4-8bc03a357fa7" />
**Добавляем игрока**
<img width="1822" height="911" alt="image" src="https://github.com/user-attachments/assets/b27d8049-80df-4b7e-95c7-e07374579e1d" />
<img width="1844" height="900" alt="image" src="https://github.com/user-attachments/assets/8522e9b1-05a8-4c2c-8197-0331c6328659" />
**"Продадим" вратаря со двора**
<img width="1831" height="922" alt="image" src="https://github.com/user-attachments/assets/e982b314-c568-4c67-b180-e409c23902e6" />
**Изменим позиция Селезнева Егора**
<img width="1822" height="919" alt="image" src="https://github.com/user-attachments/assets/63438755-b2de-4f17-87dd-5857607b36c5" />
###** 4. Вот и вce. Неплохая получилась история. Интересная, весёлая, порой немного грустная, а главное - поучительная. Она научила нас быть смелыми и не бояться вызовов, которые готовит нам жизнь. Помогала нам добиваться поставленных целей несмотря ни на что.**

## Авторы
Селезнев Е.А. - 6133
Артемов Т.М. - 6133
