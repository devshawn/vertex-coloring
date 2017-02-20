angular.module('coloring', ['ngResource', 'ui.router', 'datatables'])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    });