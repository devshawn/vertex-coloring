angular.module('coloring')
    .controller('SimulationsViewController', function (SimulationService, $stateParams, $scope) {
        var self = this;

        SimulationService.get({id: $stateParams.id}, function(results) {
            self.colorings = {
                greedy: null,
                welsh: null,
                mis: null,
                dsatur: null
            };
            
            for(var i = 0; i < results.colorings.length; i++) {
                switch(results.colorings[i].result.heuristic) {
                    case "GREEDY":
                        self.colorings.greedy = results.colorings[i];
                        break;
                    case "WELSH_POWELL":
                        self.colorings.welsh = results.colorings[i];
                        break;
                    case "MAXIMAL_INDEPENDENT_SET":
                        self.colorings.mis = results.colorings[i];
                        break;
                    case "DSATUR":
                        self.colorings.dsatur = results.colorings[i];
                        break;
                }
            }


            self.simulation = {
                id: results.id,
                graph: results.graph,
                name: results.name,
                type: results.type
            }
        });

    });

