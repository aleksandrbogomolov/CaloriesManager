var mongoDB = db.getSiblingDB("calories-manager");

mongoDB.createCollection("users");

print('populate mongodb');

mongoDB.users.update(
    {"email": "user@mail.ru"},
    {
        "_class": "com.aleksandrbogomolov.domain.User",
        "name": "User",
        "email": "user@mail.ru",
        "password": "password",
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
        "_class": "com.aleksandrbogomolov.domain.User",
        "name": "Admin",
        "email": "admin@mail.ru",
        "password": "admin",
        "enabled": true,
        "createdDate": new Date(),
        "roles": [
            "ROLE_USER",
            "ROLE_ADMIN"
        ]
    }, {"upsert": true}
);

print('populate complete');