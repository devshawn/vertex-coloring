angular.module('coloring')
    .factory('ColoringsService', function($resource) {
        return new $resource('/api/colorings/:id', null, {
            'update': { method: 'PUT'},
            'heuristics': {
                method: 'GET',
                isArray: true,
                url: '/api/colorings/heuristics'
            }
        });
    });