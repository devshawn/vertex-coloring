angular.module('coloring')
    .config(function($stateProvider) {

        var resultsState = {
            name: "results",
            url: "/results",
            templateUrl: "components/results/results.html",
            controller: "ResultsController",
            controllerAs: "resultsController"
        };

        var resultsCreateState = {
            name: "results-create",
            url: "/results/create",
            templateUrl: "components/results/results-create.html",
            controller: "ResultsCreateController",
            controllerAs: "resultsCreateController"
        };

        var resultsViewState = {
            name: "results-view",
            url: "/results/:id",
            templateUrl: "components/results/results-view.html",
            controller: "ResultsViewController",
            controllerAs: "resultsViewController"
        };

        $stateProvider.state(resultsState);
        $stateProvider.state(resultsCreateState);
        $stateProvider.state(resultsViewState);

    });