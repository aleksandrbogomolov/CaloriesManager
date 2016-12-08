# Calories manager

Веб приложение на Java с использованием Spring Boot, MongoDB, AngularJS.

На данный момент приложение еще в разработке, реализована логика, тесты и веб интерфейс, осталось добавить несколько мелочей.  
 Доступны два пользователя для входа - __User__ - __password__ и __Admin__ - __admin__, функциональность разделена по ролям.   

Для сборки и работы приложения необходимы MongoDB и Bower.

Сборка и запуск проекта c помощью Maven  
* __mvn clean package__  
* __java -jar target/caloriesmanager-0.0.1-SNAPSHOT.jar__  

Добавлена возможность запуска с помощью Docker
* __mvn clean package__
* __docker-compose up --build__

