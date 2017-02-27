angular.module('coloring')
    .controller('ResultsViewController', function (ResultService, $stateParams, $scope, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;

        self.dtSimOptions = DTOptionsBuilder.newOptions();
        self.dtSimOptions.withOption('paging', false).withOption('searching', false).withOption('info', false);
        self.dtSimColumnDefs = [
            DTColumnDefBuilder.newColumnDef(1).notSortable()
        ];

        self.dtColorsOptions = DTOptionsBuilder.newOptions();
        self.dtColorsOptions.withOption('paging', false).withOption('searching', false).withOption('info', false);
        self.dtColorsColumnDefs = [
            //DTColumnDefBuilder.newColumnDef(0).notSortable()
        ];

        self.dtTimeOptions = DTOptionsBuilder.newOptions();
        self.dtTimeOptions.withOption('paging', false).withOption('searching', false).withOption('info', false);
        self.dtTimeColumnDefs = [
            //DTColumnDefBuilder.newColumnDef(0).notSortable()
        ];

        ResultService.get({id: $stateParams.id}, function(results) {
            for(var j = 0; j < results.simulations.length; j++) {
                for(var i = 0; i < results.simulations[j].heuristics.length; i++) {
                    switch(results.simulations[j].heuristics[i].heuristic) {
                        case "GREEDY":
                            results.simulations[j].greedy = results.simulations[j].heuristics[i];
                            break;
                        case "WELSH_POWELL":
                            results.simulations[j].welsh = results.simulations[j].heuristics[i];
                            break;
                        case "MAXIMAL_INDEPENDENT_SET":
                            results.simulations[j].mis = results.simulations[j].heuristics[i];
                            break;
                        case "DSATUR":
                            results.simulations[j].dsatur = results.simulations[j].heuristics[i];
                            break;
                    }
                }
            }
            self.result = results;
        });

    });

