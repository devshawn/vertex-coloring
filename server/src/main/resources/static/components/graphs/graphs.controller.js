angular.module('coloring')
    .controller('GraphsController', function (GraphService) {
        var self = this;

        GraphService.query(function(results) {
            self.graphs = results;
        });
    });