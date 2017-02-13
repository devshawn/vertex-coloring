angular.module('coloring')
    .controller('GraphsController', function (GraphService) {
        var self = this;

        GraphService.query(function(results) {
            self.graphs = results;
        });

        self.deleteGraph = function(id) {
            GraphService.delete({id: id}, function(success) {
                console.log("Deleted");
            });
        }
    });