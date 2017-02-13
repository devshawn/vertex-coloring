angular.module('coloring')
    .factory('GraphService', function($resource) {
        return new $resource('/api/graphs/:id', null, {
            'update': { method: 'PUT'}
        });
    });