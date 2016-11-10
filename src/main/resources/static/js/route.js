'use strict';

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'home'
        })
        .when('/users', {
            templateUrl: 'views/users.html',
            controller: 'users'
        })
        .when('/meals', {
            templateUrl: 'views/meals.html',
            controller: 'meals'
        });
});
