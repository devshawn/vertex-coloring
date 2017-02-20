angular.module('coloring')
    .controller('SimulationsController', function (SimulationService, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;

        self.simulations = [];
        self.dtOptions = DTOptionsBuilder.newOptions();
        self.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable()
        ];

        SimulationService.query(function(results) {
            self.simulations = results;
        });

        self.deleteSimulation = function(id, index) {
            SimulationService.delete({id: id}, function(success) {
                self.simulations.splice(index, 1);
            });
        }
    });