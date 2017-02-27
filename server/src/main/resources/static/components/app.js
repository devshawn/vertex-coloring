angular.module('coloring', ['ngResource', 'ui.router', 'datatables', 'chart.js'])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    });