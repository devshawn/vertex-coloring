angular.module('coloring')
    .controller('GraphsController', function (GraphService, $state) {
        var self = this;

        GraphService.query(function(results) {
            self.graphs = results;
        });

        self.deleteGraph = function(id, index) {
            GraphService.delete({id: id}, function(success) {
                self.graphs.splice(index, 1);
            });
        }
    });