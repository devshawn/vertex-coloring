angular.module('coloring', ['ngResource', 'ui.router'])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    });