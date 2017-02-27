angular.module('coloring')
    .controller('ResultsCreateController', function (ResultService, $state, AlertService) {
        var self = this;

        self.createResult = function() {

            var data = {
                name: self.name,
                vertices: self.vertices
            };

            ResultService.save(data, function (response) {
                $state.go('results-view', {'id': response.id});
            }, function (error) {
                AlertService.Warning(error.data.message);
            });
        };

    });