angular.module('coloring')
    .config(function($stateProvider) {

        var simulationsState = {
            name: "simulations",
            url: "/simulations",
            templateUrl: "components/simulations/simulations.html",
            controller: "SimulationsController",
            controllerAs: "simulationsController"
        };

        var simulationsCreateState = {
            name: "simulations-create",
            url: "/simulations/create",
            templateUrl: "components/simulations/simulations-create.html",
            controller: "SimulationsCreateController",
            controllerAs: "simulationsCreateController"
        };

        var simulationsViewState = {
            name: "simulations-view",
            url: "/simulations/:id",
            templateUrl: "components/simulations/simulations-view.html",
            controller: "SimulationsViewController",
            controllerAs: "simulationsViewController"
        };

        $stateProvider.state(simulationsState);
        $stateProvider.state(simulationsCreateState);
        $stateProvider.state(simulationsViewState);

    });