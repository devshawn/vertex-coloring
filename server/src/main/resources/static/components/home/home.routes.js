angular.module('coloring')
    .config(function($stateProvider) {

        var homeState = {
            name: "home",
            url: "/",
            templateUrl: "components/home/home.html",
            controller: "HomeController",
            controllerAs: "homeController"
        };

        $stateProvider.state(homeState);
    });