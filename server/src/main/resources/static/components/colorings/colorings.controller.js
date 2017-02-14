angular.module('coloring')
    .controller('ColoringsController', function (ColoringsService, $state) {
        var self = this;

        ColoringsService.query(function(results) {
            self.colorings = results;
        });

        self.deleteColoring = function(id, index) {
            ColoringsService.delete({id: id}, function(success) {
                self.colorings.splice(index, 1);
            });
        }
    });