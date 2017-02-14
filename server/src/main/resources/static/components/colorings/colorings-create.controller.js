angular.module('coloring')
    .controller('ColoringsCreateController', function (ColoringsService, GraphService, $state, $stateParams) {
        var self = this;

        var heuristicsCallback = function(heuristics) {
            self.heuristics = heuristics;
        };

        var graphsCallback = function(graph) {
            self.graph = graph;
            ColoringsService.heuristics({}, heuristicsCallback);
        };

        if($state.$current.name == "colorings-create-coloring") {
            GraphService.get({id: $stateParams.id}, graphsCallback);
        } else {

        }

        self.generateColoring = function() {
            console.log("Clicked submit");

            if(self.heuristic) {

                var data = {
                    graphId: self.graph.id,
                    heuristic: self.heuristic
                };

                ColoringsService.save(data, function(response) {
                    console.log(response);
                    $state.go('colorings');
                });
            }
        }
    });