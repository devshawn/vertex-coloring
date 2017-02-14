angular.module('coloring')
    .controller('ColoringsViewController', function (ColoringsService, $stateParams, $scope) {
        var self = this;

        ColoringsService.get({id: $stateParams.id}, function(results) {
            self.showGraph = (results.graph.vertices < 50);
            if(self.showGraph) {
                generateVisualization(results.graph.matrix, results.result.coloring);
            }
            self.coloring = results;
        });

        var generateVisualization = function(matrix, coloring) {
            for(var i = 0; i < matrix.length; i++) {
                self.nodes.add([{id: i, label: i, group: coloring[i]}]);
            }

            for(var i = 0; i < matrix.length; i++) {
                for(var j = 0; j < i; j++) {
                    if(matrix[i][j] == 1) {
                        self.edges.add([{from: i, to: j}]);
                    }
                }
            }
        };

        self.nodes = new vis.DataSet();
        self.edges = new vis.DataSet();
        self.network_data = {
            nodes: self.nodes,
            edges: self.edges
        };
    });

