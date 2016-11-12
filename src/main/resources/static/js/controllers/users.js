'use strict';

app.controller('users', function ($scope, $http) {
    $scope.user = {
        id: '',
        name: '',
        email: '',
        password: '',
        enabled: '',
        createdDate: '',
        caloriesPerDay: '',
        roles: []
    };

    $http.get('/user').then(function (users) {
        $scope.users = users.data;
    });

    $scope.editUser = function (id) {
        var form = angular.element('#edit-user');
        $http.get('/user/' + id).then(function (user) {
            angular.forEach(user.data, function (value, key) {
                form.find("input[name='" + key + "']").val(value);
            })
        });
        form.modal();
    };

    $scope.editSubmit = function () {
        $http.post('/user', $scope.user).then(function () {
            angular.element('#edit-user').modal('hide');
        });
    }
});
