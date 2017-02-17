angular.module('coloring')
    .service('AlertService', [ '$timeout', function($timeout) {

        this.delay = 5000;

        this.alerts = [];
        this.warnings = [];
        this.notices = [];

        this.setCloseDelay = function(delay) {
            this.delay = delay;
        };

        this.removeMessage = function(msgType, message, delay) {
            var tracked = this[msgType + 's'];
            if (angular.isDefined(delay)) {
                $timeout(
                    function() {
                        var index = tracked.indexOf(message);
                        if (index > -1) {
                            tracked.splice(index,1);
                        }
                    },
                    this.delay
                );
            } else {
                var index = tracked.indexOf(message);
                if (index > -1) {
                    tracked.splice(index,1);
                }
            }
        };

        this.Message = function(msgType, message) {
            var key = msgType + 's';
            this[key].push(message);
            this.removeMessage(msgType, message, this.delay);
        };

        this.Alert = function(message) { this.Message('alert', message); };
        this.Warning = function(message) { this.Message('warning', message); };
        this.Notice = function(message) { this.Message('notice', message); };

    } ] )
    .directive('alertBox', [ 'AlertService', function(AlertService) {
        return {
            restrict: 'E',

            compile: function(element, attrs) {
                attrs.boxClass = attrs.boxClass || "alert-box";
                attrs.alertClass = attrs.alertClass || "alert";
                attrs.noticeClass = attrs.noticeClass || "success";
                attrs.warningClass = attrs.warningClass || "warning";
            },

            scope: {
                alertClass : "@",
                warningClass : "@",
                noticeClass : "@",
                boxClass : "@"
            },

            controller: function($scope) {
                $scope.alerts = AlertService.alerts;
                $scope.warnings = AlertService.warnings;
                $scope.notices = AlertService.notices;
            },
            template:  '<div ng-repeat="alert in alerts track by $index" ng-class="[boxClass, alertClass]">{{alert}}</div> \
						<div ng-repeat="warning in warnings track by $index" ng-class="[boxClass, warningClass]">{{warning}}</div> \
						<div ng-repeat="notice in notices track by $index" ng-class="[boxClass, noticeClass]">{{notice}}</div>'
        };

    } ] );