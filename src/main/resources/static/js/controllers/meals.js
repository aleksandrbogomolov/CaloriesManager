'use strict';

app.controller('meals', function ($scope, $http) {

    var mealsUrl = '/meals';

    $scope.getAll = function () {
        $http.get(mealsUrl).then(function (meals) {
            $scope.meals = meals.data;
        })
    };

    $scope.getAll();
});
