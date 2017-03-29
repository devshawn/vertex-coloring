angular.module('coloring')
    .config(function($stateProvider) {

        var collectionsState = {
            name: "collections",
            url: "/collections",
            templateUrl: "components/collections/collections.html",
            controller: "CollectionsController",
            controllerAs: "collectionsController"
        };

        var collectionsCreateState = {
            name: "collections-create",
            url: "/collections/create",
            templateUrl: "components/collections/collections-create.html",
            controller: "CollectionsCreateController",
            controllerAs: "collectionsCreateController"
        };

        var collectionsViewState = {
            name: "collections-view",
            url: "/collections/:id",
            templateUrl: "components/collections/collections-view.html",
            controller: "CollectionsViewController",
            controllerAs: "collectionsViewController"
        };

        $stateProvider.state(collectionsState);
        $stateProvider.state(collectionsCreateState);
        $stateProvider.state(collectionsViewState);

    });