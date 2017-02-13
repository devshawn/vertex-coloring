angular.module('coloring')
    .config(function($stateProvider) {

        var graphsState = {
            name: "graphs",
            url: "/graphs",
            templateUrl: "components/graphs/graphs.html",
            controller: "GraphsController",
            controllerAs: "graphsController"
        };

        $stateProvider.state(graphsState);
    });