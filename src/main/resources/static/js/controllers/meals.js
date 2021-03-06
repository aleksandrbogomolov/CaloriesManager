'use strict';

app.controller('meals', function ($scope, $http) {

    var mealsUrl = '/meals';

    $scope.getAll = function () {
        $http.get(mealsUrl).then(function (meals) {
            updateTable(meals);
        })
    };

    $scope.getFiltered = function () {
        var form = angular.element('#filter-form').serialize();
        $http.get(mealsUrl + '/filter?' + decodeURIComponent(form)).then(function (meals) {
            updateTable(meals);
        })
    };

    $scope.clearFilter = function () {
        angular.element('#filter-form').find('input', 'textarea').val('');
        $scope.getAll();
    };

    $scope.mealDetails = function (meal, isNew) {
        var form = angular.element('#edit-meal');
        if (!isNew) {
            angular.forEach(meal, function (value, key) {
                form.find("input[name='" + key + "']").val(value);
            })
        } else {
            form.find('input', 'textarea').val('');
        }
        form.modal();
    };

    $scope.submitMeal = function () {
        var meal = {};
        var form = angular.element('#details-form').serialize().split('&');
        angular.forEach(form, function (value) {
            var tmp = value.split('=');
            meal[tmp[0]] = tmp[0] != 'dateTime' ? decodeURIComponent(tmp[1]) : decodeURIComponent(tmp[1]).replace(' ', 'T');
        });
        $http.post(mealsUrl, meal).then(function () {
            angular.element('#edit-meal').modal('hide');
            $scope.getAll();
        }, function (response) {
            alert('Failed: ' + response.data.message + "\n" + prepareErrorMessage(response.data.errors));
        });
    };

    $scope.deleteMeal = function (id) {
        $http.delete(mealsUrl + '/' + id).then(function () {
            angular.element('.table').val('');
            $scope.getAll();
        }, function (response) {
            alert('Failed: ' + response.data.cause + "\n" + response.data.detail);
        })
    };

    var parseDate = function (data) {
        var month = data[1].toString().length == 1 ? '0' + data[1] : data[1];
        var day = data[2].toString().length == 1 ? '0' + data[2] : data[2];
        var hour = data[3].toString().length == 1 ? '0' + data[3] : data[3];
        var minutes = data[4].toString().length == 1 ? '0' + data[4] : data[4];
        return data[0] + '-' + month + '-' + day + ' ' + hour + ':' + minutes;
    };

    var updateTable = function (meals) {
        var mealsCollection = [];
        angular.forEach(meals.data, function (value) {
            var meal = {};
            angular.forEach(value, function (data, key) {
                meal[key] = key != 'dateTime' ? data : parseDate(data);
            });
            mealsCollection.push(meal);
        });
        $scope.rowCollection = mealsCollection;
    };

    var prepareErrorMessage = function (data) {
        var result = "";
        angular.forEach(data, function (cause) {
            result += (cause.field + ': ' + cause.defaultMessage + '\n');
        });
        return result;
    };

    $scope.getAll();
});
