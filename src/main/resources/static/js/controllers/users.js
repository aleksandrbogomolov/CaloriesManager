'use strict';

app.controller('users', function ($scope, $http) {

    var usersUrl = '/users';

    $scope.getAll = function () {
        $http.get(usersUrl).then(function (users) {
            updateTable(users);
        });
    };

    $scope.userDetails = function (id) {
        var form = angular.element('#edit-user');
        $http.get(usersUrl + '/' + id).then(function (user) {
            angular.forEach(user.data, function (value, key) {
                form.find("input[name='" + key + "']").val(key != 'createdDate' ? value : parseDate(value));
            })
        });
        form.modal();
    };

    $scope.submitUser = function () {
        var user = {};
        var form = angular.element('#details-form').serialize().split('&');
        angular.forEach(form, function (value) {
            var tmp = value.split('=');
            user[tmp[0]] = tmp[0] != 'roles' ? decodeURIComponent(tmp[1]) : decodeURIComponent(tmp[1]).split(',');
        });
        $http.post(usersUrl, user).then(function () {
            angular.element('#edit-user').modal('hide');
            angular.element('.table').val('');
            $scope.getAll();
        });
    };

    $scope.deleteUser = function (id) {
        $http.delete(usersUrl + '/' + id).then(function () {
            angular.element('.table').val('');
            $scope.getAll();
        })
    };

    $scope.changeEnabled = function (user) {
        user.enabled = !user.enabled;
        $http.post(usersUrl, user);
    };

    var parseDate = function (data) {
        var month = data[1].toString().length == 1 ? '0' + data[1] : data[1];
        var day = data[2].toString().length == 1 ? '0' + data[2] : data[2];
        return data[0] + '-' + month + '-' + day;
    };

    var updateTable = function (users) {
        var usersCollection = [];
        angular.forEach(users.data, function (value) {
            var user = {};
            angular.forEach(value, function (data, key) {
                user[key] = key != 'createdDate' ? data : parseDate(data);
            });
            usersCollection.push(user);
        });
        $scope.rowCollection = usersCollection;
    };

    $scope.getAll();
});
