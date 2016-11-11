'use strict';

app.controller('users', function ($scope, $http) {
    $scope.users = [];

    $http.get('/user').then(function (users) {
        $scope.users = users.data;
    });

    $scope.editUser = function (id) {
        var form = $('#edit-user');
        $http.get('/user/' + id).then(function (user) {
            angular.forEach(user.data, function (value, key) {
                angular.element(form).find("input[name='" + key + "']").val(value);
            })
        });
        form.modal();
    }
});
