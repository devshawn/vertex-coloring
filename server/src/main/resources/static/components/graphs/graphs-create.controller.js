angular.module('coloring')
    .controller('GraphsCreateController', function (GraphService, $state) {
        var self = this;

        self.createGraph = function() {
            console.log("Clicked submit");

            if(self.name && self.matrix) {
                console.log("We have both");

                var data = {
                    name: self.name,
                    matrix: self.matrix
                };

                GraphService.save(data, function(response) {
                    console.log(response);
                    $state.go('graphs');
                });
            }
        }

    });