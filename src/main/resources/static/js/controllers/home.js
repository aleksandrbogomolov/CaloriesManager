'use strict';

app.controller('home', function ($rootScope, $scope, $http, $location) {

    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/users/login', {headers: headers}).then(function (response) {
            var data = response.data;
            if (data.name) {
                $rootScope.authenticated = true;
                $rootScope.user = data.name;
                $rootScope.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN") > -1;
            } else {
                $rootScope.authenticated = false;
                $rootScope.admin = false;
            }
            callback && callback(true);
        }, function () {
            $rootScope.authenticated = false;
            callback && callback(false);
        });
    };

    authenticate();

    $scope.credentials = {};

    $scope.login = function () {
        authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $location.path('/');
                $scope.error = false;
            } else {
                $location.path('/');
                $scope.error = true;
            }
        });
    };

    $scope.logout = function () {
        $http.post('/logout', {}).success(function () {
            $rootScope.authenticated = false;
            $location.path('/');
        });
    };

    $scope.newUserForm = function () {
        var form = angular.element('#edit-user');
        form.find("input[name='id']").val('');
        form.find("input[name='enabled']").val(true);
        form.find("input[name='roles']").val(['ROLE_USER']);
        form.find("input[name='createdDate']").val([new Date().toISOString()]);
        form.modal();
    };
});
