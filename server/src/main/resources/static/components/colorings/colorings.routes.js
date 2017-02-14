angular.module('coloring')
    .config(function($stateProvider) {

        var coloringsState = {
            name: "colorings",
            url: "/colorings",
            templateUrl: "components/colorings/colorings.html",
            controller: "ColoringsController",
            controllerAs: "coloringsController"
        };

        var coloringsCreateState = {
            name: "colorings-create",
            url: "/colorings/create",
            templateUrl: "components/colorings/colorings-create.html",
            controller: "ColoringsCreateController",
            controllerAs: "coloringsCreateController"
        };

        var coloringsCreateColoringState = {
            name: "colorings-create-coloring",
            url: "/colorings/create/:id",
            templateUrl: "components/colorings/colorings-create-coloring.html",
            controller: "ColoringsCreateController",
            controllerAs: "coloringsCreateController"
        };

        var coloringsViewState = {
            name: "colorings-view",
            url: "/colorings/:id",
            templateUrl: "components/colorings/colorings-view.html",
            controller: "ColoringsViewController",
            controllerAs: "coloringsViewController"
        };

        $stateProvider.state(coloringsState);
        $stateProvider.state(coloringsCreateState);
        $stateProvider.state(coloringsCreateColoringState);
        $stateProvider.state(coloringsViewState);
    });