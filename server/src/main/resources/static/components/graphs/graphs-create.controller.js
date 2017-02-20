angular.module('coloring')
    .controller('GraphsCreateController', function (GraphService, $state, AlertService) {
        var self = this;

        self.createGraph = function() {

            if(self.name && self.matrix) {

                if(!validateMatrix(self.matrix)) {
                    AlertService.Warning("The entered lower triangular matrix is invalid.");
                } else {
                    var data = {
                        name: self.name,
                        matrix: lowerTriangularToMatrix(self.matrix)
                    };

                    GraphService.save(data, function(response) {
                        $state.go('graphs-view', { 'id': response.id });
                    }, function(error) {
                        if(error.status === 500) {
                            AlertService.Warning(error.data.message);
                        }
                    });
                }
            }
        };

        self.generateGraph = function() {
            if(self.name2 && self.vertices && self.edges != null) {

                var data = {
                    name: self.name2,
                    vertices: self.vertices,
                    edges: self.edges
                };

                GraphService.save(data, function(response) {
                    $state.go('graphs-view', { 'id': response.id });
                }, function(error) {
                    if(error.status === 500) {
                        AlertService.Warning(error.data.message);
                    }
                });
            }
        };

        var validateMatrix = function(string) {
            var lines = string.split('\n');
            for(var i = 1; i < lines.length + 1; i++) {
                if(lines[i-1].trim().length != i) {
                    return false;
                }
            }

            return true;
        };

        var lowerTriangularToMatrix = function(string) {
            var lines = string.split('\n');
            var array = [];
            var str = "";

            for(var i = 0; i < lines.length; i++) {
                array.push(Array.apply(null, Array(lines.length)).map(function () {}));
            }

            for(var i = 0; i < lines.length; i++) {
                var line = lines[i].split('');
                for(var j = 0; j < line.length; j++) {
                    array[i][j] = line[j];
                    array[j][i] = line[j];
                }
            }

            for(var i = 0; i < lines.length; i++) {
                if(i != 0) {
                    str += "\n";
                }

                for(var j = 0; j < lines.length; j++) {
                    str += array[i][j]
                }
            }

            return str;

        };

    });