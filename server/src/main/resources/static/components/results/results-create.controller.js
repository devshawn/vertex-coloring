angular.module('coloring')
    .controller('ResultsCreateController', function (ResultService, $state, AlertService) {
        var self = this;
        self.start = 0;
        self.end = 100;
        self.increment = 1;

        self.createResult = function() {

            if(self.start < 0 || self.start > 100) {
                AlertService.Warning("The starting edge percentage must be between 0 and 100.");
                return;
            }

            if(self.end < 0 || self.end > 100) {
                AlertService.Warning("The ending edge percentage must be between 0 and 100.");
                return;
            }

            if(self.start >= self.end) {
                AlertService.Warning("The starting percentage must be less than the ending percentage.");
                return;
            }

            if(self.increment < 1 || self.increment > 10) {
                AlertService.Warning("The edge percentage increment must be between 1 and 10.");
                return;
            }

            var data = {
                name: self.name,
                vertices: self.vertices,
                runs: self.runs,
                start: self.start,
                end: self.end,
                increment: self.increment
            };

            ResultService.save(data, function (response) {
                $state.go('results-view', {'id': response.id});
            }, function (error) {
                AlertService.Warning(error.data.message);
            });
        };

    });