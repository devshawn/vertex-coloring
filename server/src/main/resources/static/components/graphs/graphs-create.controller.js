angular.module('coloring')
    .controller('GraphsCreateController', function (GraphService, $state) {
        var self = this;

        self.createGraph = function() {
            console.log("Clicked submit");

            if(self.name && self.matrix) {
                console.log("We have both");

                var data = {
                    name: self.name,
                    matrix: self.matrix
                };

                GraphService.save(data, function(response) {
                    console.log(response);
                    $state.go('graphs-view', { 'id': response.id });
                });
            }
        };

        self.generateGraph = function() {
            console.log(self.edges.length);
            if(self.name2 && self.vertices && self.edges != null) {
                console.log("A");

                var data = {
                    name: self.name2,
                    vertices: self.vertices,
                    edges: self.edges
                };

                GraphService.save(data, function(response) {
                    console.log(response);
                    $state.go('graphs-view', { 'id': response.id });
                }, function(error) {
                    console.log(error);
                });
            }
        };

    });