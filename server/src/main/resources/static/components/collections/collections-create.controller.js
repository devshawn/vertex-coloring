angular.module('coloring')
    .controller('CollectionsCreateController', function (CollectionService, $state, AlertService) {
        var self = this;

        self.createCollection = function() {

            console.log(self.results);
            console.log(self.results.trim().split("\n"));

            var data = {
                name: self.name,
                results: self.results.trim()
            };

            CollectionService.save(data, function (response) {
                $state.go('collections-view', {'id': response.id});
            }, function (error) {
                AlertService.Warning(error.data.message);
            });
        };

    });