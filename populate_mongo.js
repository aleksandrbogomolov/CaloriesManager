var mongoDB = db.getSiblingDB("calories-manager");

mongoDB.createCollection("users");
mongoDB.createCollection("meals");

print('populate mongodb');

mongoDB.users.update(
    {"email": "user@mail.ru"},
    {
        "_id": "1",
        "_class": "com.aleksandrbogomolov.domain.User",
        "name": "User",
        "email": "user@mail.ru",
        "password": "password",
        "caloriesPerDay": 2000,
        "enabled": true,
        "createdDate": new Date(),
        "roles": [
            "ROLE_USER"
        ]
    }, {"upsert": true}
);

mongoDB.users.update(
    {"email": "admin@mail.ru"},
    {
        "_id": "2",
        "_class": "com.aleksandrbogomolov.domain.User",
        "name": "Admin",
        "email": "admin@mail.ru",
        "password": "admin",
        "caloriesPerDay": 2500,
        "enabled": true,
        "createdDate": new Date(),
        "roles": [
            "ROLE_USER",
            "ROLE_ADMIN"
        ]
    }, {"upsert": true}
);

mongoDB.meals.update(
    {"_id": "3"},
    {
        "_id": "3",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-01-16T05:25:50.000Z"),
        "description": "Завтрак",
        "calories": 500,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "4"},
    {
        "_id": "4",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-16T10:25:50.000Z"),
        "description": "Обед",
        "calories": 1000,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "5"},
    {
        "_id": "5",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-20T11:00:00.000Z"),
        "description": "Полдник",
        "calories": 995,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "6"},
    {
        "_id": "6",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-20T14:00:00.000Z"),
        "description": "Ужин",
        "calories": 700,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "7"},
    {
        "_id": "7",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-21T10:20:00.000Z"),
        "description": "Обед",
        "calories": 1000,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "8"},
    {
        "_id": "8",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-21T06:05:00.000Z"),
        "description": "Завтрак",
        "calories": 400,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

mongoDB.meals.update(
    {"_id": "9"},
    {
        "_id": "9",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-21T13:00:00.000Z"),
        "description": "Полдник",
        "calories": 1000,
        "user": {
            "$ref": "users",
            "$id": "1"
        }
    }, {"upsert": true});

print('populate complete');
