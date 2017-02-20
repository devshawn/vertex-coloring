angular.module('coloring').filter('selectedHeuristics', function() {
    return function(colorings, heuristics) {
        return colorings.filter(function(coloring) {
            if (heuristics.indexOf(coloring.result.heuristic) != -1) {
                return true;
            }
            return false;

        });
    };
}).filter('selectedTypes', function() {
    return function(colorings, types) {
        return colorings.filter(function(coloring) {
            if (types.indexOf(coloring.type) != -1) {
                return true;
            }
            return false;

        });
    };
});