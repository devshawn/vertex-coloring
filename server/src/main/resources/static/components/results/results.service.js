angular.module('coloring')
    .factory('ResultService', function($resource) {
        return new $resource('/api/results/:id', null, {
            'update': { method: 'PUT'}
        });
    });