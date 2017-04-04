angular.module('coloring')
    .controller('CollectionsViewController', function (CollectionService, $stateParams, $scope, DTOptionsBuilder, DTColumnDefBuilder) {
        var self = this;
        self.colors = [];
        self.time = [];
        self.compare = [];
        self.percentDifference = [];
        self.timePercentage = [];
        self.timePercentDifference = [];
        self.minimumPercentDifference = [];
        self.maximumPercentDifference = [];

        CollectionService.get({id: $stateParams.id}, function(results) {
            self.collection = results;

            // percent error
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
                    labels: []
                };

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

                toPush.data = [greedywp, greedydsatur, wpdsatur];
                // toPush.data = [greedywp, greedymis, greedydsatur, wpmis, wpdsatur, misdsatur];

                self.compare.push(toPush);
            }

            // percent difference
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
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
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.percentDifference);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.percentDifference);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.percentDifference);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.percentDifference);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.percentDifference);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.percentDifference);
                }

                toPush.data = [greedywp, greedydsatur, wpdsatur];

                self.percentDifference.push(toPush);
            }

            // minimum percent difference
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
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
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.minimumPercentDifference);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.minimumPercentDifference);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.minimumPercentDifference);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.minimumPercentDifference);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.minimumPercentDifference);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.minimumPercentDifference);
                }

                toPush.data = [greedywp, greedydsatur, wpdsatur];

                self.minimumPercentDifference.push(toPush);
            }

            // maximum percent difference
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
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
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.maximumPercentDifference);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.maximumPercentDifference);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.maximumPercentDifference);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.maximumPercentDifference);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.maximumPercentDifference);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.maximumPercentDifference);
                }

                toPush.data = [greedywp, greedydsatur, wpdsatur];

                self.maximumPercentDifference.push(toPush);
            }

            // time percent error
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
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
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.timePercentage);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.timePercentage);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.timePercentage);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.timePercentage);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.timePercentage);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.timePercentage);
                }

                toPush.data = [greedywp, greedydsatur, wpdsatur];

                self.timePercentage.push(toPush);
            }

            // time percent difference
            for(var i = 0; i < self.collection.results.length; i++) {
                var toPush = {
                    name: self.collection.results[i].name,
                    vertices: self.collection.results[i].vertices,
                    data: [],
                    series: ["GRDY-WP", "GRDY-DSAT", "WP-DSAT"],
                    // series: ["GRDY-WP", "GRDY-MIS", "GRDY-DSAT", "WP-MIS", "WP-DSAT", "MIS-DSAT"],
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
                    greedywp.push(self.collection.results[i].comparisonSummaries[j].greedy_wp.timePercentDifference);
                    greedymis.push(self.collection.results[i].comparisonSummaries[j].greedy_mis.timePercentDifference);
                    greedydsatur.push(self.collection.results[i].comparisonSummaries[j].greedy_dsatur.timePercentDifference);
                    wpmis.push(self.collection.results[i].comparisonSummaries[j].wp_mis.timePercentDifference);
                    wpdsatur.push(self.collection.results[i].comparisonSummaries[j].wp_dsatur.timePercentDifference);
                    misdsatur.push(self.collection.results[i].comparisonSummaries[j].mis_dsatur.timePercentDifference);
                }

                toPush.data = [greedywp, greedydsatur, wpdsatur];

                self.timePercentDifference.push(toPush);
            }

            self.compare.sort(self.sortingFunction);
            self.percentDifference.sort(self.sortingFunction);
            self.timePercentDifference.sort(self.sortingFunction);



        });

        self.sortingFunction = function(a, b) {
            return a.vertices - b.vertices;
        };

        $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
        $scope.colors = ['#ff6384', '#46BFBD', '#FDB45C'];
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

