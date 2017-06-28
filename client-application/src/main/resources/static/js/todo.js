angular.module('hello', ['ngRoute', 'afOAuth2']).config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'todo.html',
        controller : 'todo',
        controllerAs : 'controller'
    }).otherwise('/');
}).controller('navigation',

    function($rootScope, $http, $location, $route) {

        var self = this;

        self.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        $rootScope.authenticated = false;
        $http.get('user').then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
                $rootScope.userName = response.data.name;
            }
        }, function(response) {
            console.log(response);
        });

        self.logout = function() {
            $http.post('logout', {}).finally(function() {
                $rootScope.authenticated = false;
                $rootScope.userName = "";
                $location.path("/");
            });
        }
    }).controller('todo', function($http) {
    var self = this;
    $http.get('http://localhost:9001/api/tasks').then(function(response) {
        self.todos = response.data;
    })
});
