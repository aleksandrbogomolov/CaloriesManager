'use strict';

var app = angular.module('calories-manager', ['ngRoute', 'pascalprecht.translate', 'smart-table', 'ui.bootstrap.datetimepicker', 'ui.dateTimeInput']);

app.config(function ($routeProvider, $httpProvider) {
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
        })
        .otherwise('/');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

app.controller('TranslateController', function($translate, $scope) {
    $scope.changeLanguage = function (langKey) {
        $translate.use(langKey);
    };

    $scope.useLang = function () {
        if ($translate.use() == 'ru') return true;
    }
});

app.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: 'translate/',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('ru');
});
