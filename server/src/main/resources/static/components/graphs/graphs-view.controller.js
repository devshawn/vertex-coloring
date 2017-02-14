angular.module('coloring')
    .controller('GraphsViewController', function (GraphService, $stateParams, $scope) {
        var self = this;

        GraphService.get({id: $stateParams.id}, function(results) {
            self.graph = results;
            self.showGraph = (results.vertices < 50);
            if(self.showGraph) {
                generateVisualization(results.matrix);
            }
            self.graph.matrix = arrayGraphToString(results.matrix);

        });

        var arrayGraphToString = function(array) {
            var str = "";
            for(var i = 0; i < array.length; i++) {
                for(var j = 0; j < array.length; j++) {
                    str += array[i][j];
                }
                str += "\n";
            }
            return str;
        };

        var generateVisualization = function(matrix) {
            for(var i = 0; i < matrix.length; i++) {
                self.nodes.add([{id: i, label: i}]);
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

