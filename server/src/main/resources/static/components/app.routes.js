angular.module('coloring')
    .config(function ($stateProvider) {
        $stateProvider.state("404", {
            url: "*path",
            templateUrl: "components/shared/404.html"
        });
    });