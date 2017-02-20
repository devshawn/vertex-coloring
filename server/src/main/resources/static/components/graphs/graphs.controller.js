angular.module('coloring')
    .controller('GraphsController', function (GraphService, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;

        self.graphs = [];
        self.dtOptions = DTOptionsBuilder.newOptions();
        self.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable()
        ];

        GraphService.query(function(results) {
            self.graphs = results;
        });

        self.deleteGraph = function(id, index) {
            GraphService.delete({id: id}, function(success) {
                self.graphs.splice(index, 1);
            });
        }
    });