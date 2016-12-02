'use strict';

app.controller('users', function ($scope, $http) {

    var usersUrl = '/users';

    $scope.getAll = function () {
        $http.get(usersUrl).then(function (users) {
            updateTable(users);
        });
    };

    $scope.userDetails = function (user, isAdmin) {
        var form = angular.element('#edit-user');
        if (isAdmin) {
            angular.forEach(user, function (value, key) {
                form.find("input[name='" + key + "']").val(value);
            });
        } else {
            $http.get(usersUrl + '/info/' + user).then(function (userInfo) {
                angular.forEach(userInfo.data, function (value, key) {
                    form.find("input[name='" + key + "']").val(key != 'createdDate' ? value : parseDate(value));
                });
            })
        }
        form.modal();
    };

    // $scope.submitUser = function (isNew) {
    $scope.submitUser = function () {
        var user = {};
        var form = angular.element('#details-form').serialize().split('&');
        angular.forEach(form, function (value) {
            var tmp = value.split('=');
            user[tmp[0]] = tmp[0] != 'roles' ? decodeURIComponent(tmp[1]) : decodeURIComponent(tmp[1]).split(',');
        });
        // $http.post(isNew ? usersUrl + '/register' : usersUrl, user).then(function () {
        $http.post(user.id == '' ? usersUrl + '/register' : usersUrl, user).then(function () {
            angular.element('#edit-user').modal('hide');
            angular.element('.table').val('');
            $scope.getAll();
        });
    };

    $scope.deleteDialog = function (id) {
        var form = angular.element('#delete-user');
        form.find("input[name='delete-id']").val(id);
        form.modal();
    };

    $scope.deleteUser = function () {
        $http.delete(usersUrl + '/' + angular.element('#dialog-form').serialize().split('=')[1]).then(function () {
            angular.element('.table').val('');
            $scope.getAll();
            angular.element('#delete-user').modal('hide');
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
