angular.module('coloring')
    .factory('SimulationService', function($resource) {
        return new $resource('/api/simulations/:id', null, {
            'update': { method: 'PUT'}
        });
    });