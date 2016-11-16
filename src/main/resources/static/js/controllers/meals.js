'use strict';

app.controller('meals', function ($scope, $http) {

    var mealsCollection = [];

    var mealsUrl = '/meals';

    $scope.getAll = function () {
        $http.get(mealsUrl).then(function (meals) {
            angular.forEach(meals.data, function (value) {
                var meal = {};
                angular.forEach(value, function (data, key) {
                    meal[key] = data;
                    if (key == 'dateTime') meal[key] = parseDate(data);
                });
                mealsCollection.push(meal);
            });
            $scope.meals = mealsCollection;
        })
    };

    $scope.mealDetails = function (id) {
        var form = angular.element('#edit-meal');
        $http.get(mealsUrl + '/' + id).then(function (meal) {
            angular.forEach(meal.data, function (value, key) {
                form.find("input[name='" + key + "']").val(key == 'dateTime' ? parseDate(value) : value);
            })
        });
        form.modal();
    };

    $scope.submitMeal = function () {
        var meal = {};
        var form = angular.element('#details-form').serialize().split('&');
        angular.forEach(form, function (value) {
            var tmp = value.split('=');
            meal[tmp[0]] = tmp[1];
            if (tmp[0] == 'createdDate') {
                var str = tmp[1].replace(/%2C/g, '-').replace(' ', 'T');
                if (str.length < 10) str = [str.slice(0, 8), '0', str.slice(8)].join('');
                meal[tmp[0]] = str;
            }
        });
        $http.post(mealsUrl, meal).then(function () {
            angular.element('#edit-meal').modal('hide');
            angular.element('.table').val('');
            $scope.getAll();
        });
    };

    $scope.deleteMeal = function (id) {
        $http.delete(mealsUrl + '/' + id).then(function () {
            angular.element('.table').val('');
            $scope.getAll();
        })
    };

    $scope.getters = {
        dateTime: function (value) {
            return value.dateTime.getDate();
        },
        calories: function (value) {
            return value.calories;
        }
    };

    var parseDate = function (data) {
        return new Date(data[0], data[1], data[2], data[3], data[4])
    };

    $scope.getAll();
});
