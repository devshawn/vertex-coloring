angular.module('coloring')
    .controller('ResultsController', function (ResultService, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;

        self.results = [];
        self.dtOptions = DTOptionsBuilder.newOptions();
        self.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable()
        ];

        ResultService.query(function(results) {
            self.results = results;
        });

        self.deleteResult = function(id, index) {
            ResultService.delete({id: id}, function(success) {
                self.results.splice(index, 1);
            });
        }
    });