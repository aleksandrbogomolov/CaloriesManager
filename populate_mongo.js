var mongoDB = db.getSiblingDB("calories-manager");

mongoDB.createCollection("users");

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

print('populate complete');
