angular.module('coloring')
    .config(function($stateProvider) {

        var graphsState = {
            name: "graphs",
            url: "/graphs",
            templateUrl: "components/graphs/graphs.html",
            controller: "GraphsController",
            controllerAs: "graphsController"
        };

        var graphsCreateState = {
            name: "graphs-create",
            url: "/graphs/create",
            templateUrl: "components/graphs/graphs-create.html",
            controller: "GraphsCreateController",
            controllerAs: "graphsCreateController"
        };

        $stateProvider.state(graphsState);
        $stateProvider.state(graphsCreateState);
    });