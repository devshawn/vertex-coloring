angular.module('coloring')
    .directive('visNetwork', function () {
        return {
            restrict: 'EA',
            transclude: false,
            scope: {
                data: '=',
                options: '=',
                events: '='
            },
            link: function (scope, element, attr) {
                var networkEvents = [
                    'click',
                    'doubleClick',
                    'oncontext',
                    'hold',
                    'release',
                    'selectNode',
                    'selectEdge',
                    'deselectNode',
                    'deselectEdge',
                    'dragStart',
                    'dragging',
                    'dragEnd',
                    'hoverNode',
                    'hoverEdge',
                    'blurNode',
                    'blurEdge',
                    'zoom',
                    'showPopup',
                    'hidePopup',
                    'startStabilizing',
                    'stabilizationProgress',
                    'stabilizationIterationsDone',
                    'stabilized',
                    'resize',
                    'initRedraw',
                    'beforeDrawing',
                    'afterDrawing',
                    'animationFinished'

                ];

                var network = null;

                var options = {
                    width: '100%',
                    height: '600px',
                    nodes: {
                        color: '#aaa',
                        shape: 'circle',
                        size: 10,
                        font: {
                            size: 10,
                            color: '#000000'
                        },
                        borderWidth: 2
                    },
                    edges: {
                        color: '#000000',
                        width: 2,
                        smooth: {
                            enabled: false
                        }
                    },
                    physics: {
                        solver: 'repulsion',
                        repulsion: {
                            nodeDistance: 80
                        },
                        stabilization: {
                            enabled: true,
                            iterations: 180, // maximum number of iteration to stabilize
                            updateInterval: 10,
                            onlyDynamicEdges: false,
                            fit: true
                        }
                    }
                };

                scope.$watch('data', function () {
                    // Sanity check
                    if (scope.data == null) {
                        return;
                    }

                    // If we've actually changed the data set, then recreate the graph
                    // We can always update the data by adding more data to the existing data set
                    if (network != null) {
                        network.destroy();
                    }

                    network = new vis.Network(element[0], scope.data, options);
                    network.fit();
                    network.on('stabilized', function() {
                        //network.fit();
                    });

                    angular.forEach(scope.events, function (callback, event) {
                        if (networkEvents.indexOf(String(event)) >= 0) {
                            network.on(event, callback);
                        }
                    });

                    if (scope.events != null && scope.events.onload != null &&
                        angular.isFunction(scope.events.onload)) {
                        scope.events.onload(network);
                    }
                });

                // scope.$watchCollection('options', function (options) {
                //     if (network == null) {
                //         return;
                //     }
                //     network.setOptions(options);
                // });
            }
        };
    });

