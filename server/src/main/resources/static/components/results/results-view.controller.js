angular.module('coloring')
    .controller('ResultsViewController', function (ResultService, $stateParams, $scope, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;
        self.time = {
            data: [],
            series: ["Greedy", "Welsh-Powell", "MIS", "DSATUR"],
            labels: []
        };

        self.colors = {
            data: [],
            series: ["Greedy", "Welsh-Powell", "MIS", "DSATUR"],
            labels: []
        };

        self.colorsPercentage = {
            data: [],
            series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
            labels: []
        };

        self.compare = {
            data: [],
            series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
            labels: []
        };

        self.timeCompare = {
            data: [],
            series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
            labels: []
        };

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

            // build graphs
            var greedy = [];
            var welsh = [];
            var mis = [];
            var dsatur = [];

            for(var i = 0; i < results.simulations.length; i++) {
                self.time.labels.push(results.simulations[i].smallName);
                greedy.push(results.simulations[i].greedy.time);
                welsh.push(results.simulations[i].welsh.time);
                mis.push(results.simulations[i].mis.time);
                dsatur.push(results.simulations[i].dsatur.time);
            }

            self.time.data = [greedy, welsh, mis, dsatur];

            greedy = [];
            welsh = [];
            mis = [];
            dsatur = [];

            for(var i = 0; i < results.simulations.length; i++) {
                self.colors.labels.push(results.simulations[i].smallName);
                greedy.push(results.simulations[i].greedy.colors);
                welsh.push(results.simulations[i].welsh.colors);
                mis.push(results.simulations[i].mis.colors);
                dsatur.push(results.simulations[i].dsatur.colors);
            }

            self.colors.data = [greedy, welsh, mis, dsatur];


            var greedywp = [];
            var greedymis = [];
            var greedydsatur = [];
            var wpmis = [];
            var wpdsatur = [];
            var misdsatur = [];

            for(var i = 0; i < results.comparisonSummaries.length; i++) {
                self.compare.labels.push(results.comparisonSummaries[i].name);
                greedywp.push(results.comparisonSummaries[i].greedy_wp.colors);
                greedymis.push(results.comparisonSummaries[i].greedy_mis.colors);
                greedydsatur.push(results.comparisonSummaries[i].greedy_dsatur.colors);
                wpmis.push(results.comparisonSummaries[i].wp_mis.colors);
                wpdsatur.push(results.comparisonSummaries[i].wp_dsatur.colors);
                misdsatur.push(results.comparisonSummaries[i].mis_dsatur.colors);
            }

            self.compare.data = [greedywp, greedymis, greedydsatur, wpmis, wpdsatur, misdsatur];

            greedywp = [];
            greedymis = [];
            greedydsatur = [];
            wpmis = [];
            wpdsatur = [];
            misdsatur = [];

            for(var i = 0; i < results.comparisonSummaries.length; i++) {
                self.colorsPercentage.labels.push(results.comparisonSummaries[i].name);
                greedywp.push(results.comparisonSummaries[i].greedy_wp.percentDifference);
                greedymis.push(results.comparisonSummaries[i].greedy_mis.percentDifference);
                greedydsatur.push(results.comparisonSummaries[i].greedy_dsatur.percentDifference);
                wpmis.push(results.comparisonSummaries[i].wp_mis.percentDifference);
                wpdsatur.push(results.comparisonSummaries[i].wp_dsatur.percentDifference);
                misdsatur.push(results.comparisonSummaries[i].mis_dsatur.percentDifference);
            }

            self.colorsPercentage.data = [greedywp, greedymis, greedydsatur, wpmis, wpdsatur, misdsatur];

            greedywp = [];
            greedymis = [];
            greedydsatur = [];
            wpmis = [];
            wpdsatur = [];
            misdsatur = [];

            for(var i = 0; i < results.comparisonSummaries.length; i++) {
                self.timeCompare.labels.push(results.comparisonSummaries[i].name);
                greedywp.push(results.comparisonSummaries[i].greedy_wp.timePercentDifference);
                greedymis.push(results.comparisonSummaries[i].greedy_mis.timePercentDifference);
                greedydsatur.push(results.comparisonSummaries[i].greedy_dsatur.timePercentDifference);
                wpmis.push(results.comparisonSummaries[i].wp_mis.timePercentDifference);
                wpdsatur.push(results.comparisonSummaries[i].wp_dsatur.timePercentDifference);
                misdsatur.push(results.comparisonSummaries[i].mis_dsatur.timePercentDifference);
            }

            self.timeCompare.data = [greedywp, greedymis, greedydsatur, wpmis, wpdsatur, misdsatur];

            self.result = results;
        });


        $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
        $scope.options = {
            scales: {
                yAxes: [
                    {
                        id: 'y-axis-1',
                        type: 'linear',
                        display: true,
                        position: 'left',
                        ticks: {
                            beginAtZero: true
                        }
                    }
                ]
            },
            elements: {
                line: {
                    fill: false
                }
            },
            legend: {
                display: true,
                position: 'top'
            }
        }

    });

