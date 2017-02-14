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

        var graphsViewState = {
            name: "graphs-view",
            url: "/graphs/:id",
            templateUrl: "components/graphs/graphs-view.html",
            controller: "GraphsViewController",
            controllerAs: "graphsViewController"
        };

        $stateProvider.state(graphsState);
        $stateProvider.state(graphsCreateState);
        $stateProvider.state(graphsViewState);
    });