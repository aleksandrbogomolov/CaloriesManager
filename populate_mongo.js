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
    {"description": "Завтрак"},
    {
        "_id": "3",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-16T07:25:50"),
        "description": "Завтрак",
        "calories": 500,
        "user": {"$ref": "users", "$id": "1"}
    }, {"upsert": true}
);

mongoDB.meals.update(
    {"description": "Обед"},
    {
        "_id": "4",
        "_class": "com.aleksandrbogomolov.domain.Meal",
        "dateTime": new Date("2016-11-16T12:25:50"),
        "description": "Обед",
        "calories": 1000,
        "user": {"$ref": "users", "$id": "1"}
    }, {"upsert": true}
);

print('populate complete');
