angular.module('coloring')
    .factory('CollectionService', function($resource) {
        return new $resource('/api/collections/:id', null, {
            'update': { method: 'PUT'}
        });
    });