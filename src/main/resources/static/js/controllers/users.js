'use strict';

app.controller('users', function ($scope, $http) {

    var userUrl = '/user';

    $scope.getAll = function () {
        $http.get(userUrl).then(function (users) {
            $scope.users = users.data;
        });
    };

    $scope.userDetails = function (id) {
        var form = angular.element('#edit-user');
        $http.get(userUrl + '/' + id).then(function (user) {
            angular.forEach(user.data, function (value, key) {
                form.find("input[name='" + key + "']").val(value);
            })
        });
        form.modal();
    };

    $scope.submitUser = function () {
        var user = {};
        var form = angular.element('#details-form').serialize().split('&');
        angular.forEach(form, function (value) {
            var tmp = value.split('=');
            user[tmp[0]] = tmp[1];
            if (tmp[0] == 'email') user[tmp[0]] = tmp[1].replace('%40', '@');
            if (tmp[0] == 'roles') user[tmp[0]] = tmp[1].split('%2C');
            if (tmp[0] == 'createdDate') user[tmp[0]] = tmp[1].replace(/%2C/g, '-');
        });
        $http.post(userUrl, user).then(function () {
            angular.element('#edit-user').modal('hide');
            angular.element('.table').val('');
            $scope.getAll();
        });
    };

    $scope.deleteUser = function (id) {
        $http.delete(userUrl + '/' + id).then(function () {
            angular.element('.table').val('');
            $scope.getAll();
        })
    };

    $scope.getAll();
});
