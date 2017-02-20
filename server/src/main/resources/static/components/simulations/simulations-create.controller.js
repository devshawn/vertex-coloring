angular.module('coloring')
    .controller('SimulationsCreateController', function (SimulationService, GraphService, $state, AlertService) {
        var self = this;
        self.simple = {};

        GraphService.query({}, function(results) {
            self.graphs = results;
        });

        self.runSimpleSimulation = function() {

            var data = {
                name: self.simple.name,
                graphId: self.simple.graph,
                type: 'SIMPLE'
            };

            SimulationService.save(data, function (response) {
                $state.go('simulations-view', {'id': response.id});
            }, function (error) {
                AlertService.Warning(error.data.message);
            });
        }

    });