'use strict';

app.controller('home', function ($rootScope, $scope, $http, $location) {

    var authenticate = function (credentials, callback) {

        var headers = credentials ? {
            authorization: "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/users/login', {headers: headers}).success(function (data) {
            $rootScope.authenticated = !!data.name;
            callback && callback();
        }).error(function () {
            $rootScope.authenticated = false;
            callback && callback();
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
                $location.path('/login');
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
});
