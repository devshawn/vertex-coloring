angular.module('coloring')
    .controller('CollectionsViewController', function (CollectionService, $stateParams, $scope, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;
        self.compare = [];

        CollectionService.get({id: $stateParams.id}, function(results) {
            self.collection = results;

            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    data: [],
                    series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
                    labels: []
                }

                var greedywp = [];
                var greedymis = [];
                var greedydsatur = [];
                var wpmis = [];
                var wpdsatur = [];
                var misdsatur = [];

                for(var j = 0; j < self.collection.results[i].comparisonSummaries.length; j++) {
                    toPush.labels.push(self.collection.results[i].comparisonSummaries[j].name);
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.colors);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.colors);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.colors);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.colors);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.colors);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.colors);
                }

                toPush.data = [greedywp, greedymis, greedydsatur, wpmis, wpdsatur, misdsatur];

                self.compare.push(toPush);
            }

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

