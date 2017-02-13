angular.module('coloring')
    .controller('NavbarController', function ($scope, $location) {
        var self = this;
        self.isActive = isActive;

        $scope.test = "hello";

        function isActive(viewLocation) {
            return (viewLocation == "/" && $location.url() != "/") ? false : $location.path().indexOf(viewLocation) == 0;
        }

    });