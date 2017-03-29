angular.module('coloring')
    .controller('CollectionsController', function (CollectionService, $state, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;

        self.results = [];
        self.dtOptions = DTOptionsBuilder.newOptions();
        self.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable()
        ];

        CollectionService.query(function(results) {
            self.collections = results;
        });

        self.deleteCollection = function(id, index) {
            CollectionService.delete({id: id}, function(success) {
                self.collections.splice(index, 1);
            });
        }
    });