angular.module('coloring')
    .controller('ColoringsController', function (ColoringsService, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;
        self.colorings = [];

        self.dtOptions = DTOptionsBuilder.newOptions();
        self.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable()
        ];

        ColoringsService.query(function(results) {
            self.colorings = results;
        });

        self.deleteColoring = function(id, index) {
            ColoringsService.delete({id: id}, function(success) {
                self.colorings.splice(index, 1);
            });
        };
    });