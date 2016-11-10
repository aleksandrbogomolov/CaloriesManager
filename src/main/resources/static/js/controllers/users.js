'use strict';

app.controller('users', function ($scope, $http) {
    $scope.users = [];
    var users = $http.get('/user');
    users.then(function (users) {
        $scope.users = users.data;
        var homeLi = $.getElementById('#home');
        homeLi.$removeClass('active');
        var userLi = $.getElementById('#users');
        userLi.addClass('active');
    })
});
