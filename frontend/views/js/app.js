var app = angular.module('boraApp', ['ui.router']);

// TODO add url in the config
var global_url_test="http://localhost:8080";
var global_url_front_test="http://localhost:8000";
var image_url=global_url_test + "/image/";

app.controller('ViewEventController', function ViewEventController($scope, $http, $stateParams, $location) {

  $http.get(global_url_test + '/event/' + $stateParams.id)
    .then(function (result) {
      $scope.event = result.data;
  });

  $scope.confirm = function() {
    var config = {
      headers : {
          'Content-Type': 'application/json'
      }
    }

    dataObj = {"name" : $scope.attendee.name }

    var url = global_url_test + '/event/confirm/' + $stateParams.id;
    var data = JSON.stringify(dataObj);
    $http.post(url, data, config)
    .then(function(response) {
          console.log(response);
          alert('Confirmado !');
          $location.path("/#");
    });        
  }

});

app.controller('CreateEventController', function($scope, $http, $location) {
  console.log("Controller");
  $scope.newEvent = {};

  $scope.showEvent = function() {
    console.log(">>>");
    var dataObj = {"name": $scope.newEvent.name, "local": $scope.newEvent.local};

    var config = {
      headers : {
          'Content-Type': 'application/json'
      }
    }

    var url = global_url_test + '/event';
    var data = JSON.stringify(dataObj);
    $http.post(url, data, config)
    .then(function(response) {
          alert('Evento criado !');
          $location.path("/#");
    });
  };

  console.log($scope);

});

app.controller('ViewEventsController', function($scope, $http, $location) {
  $scope.image_url = image_url;
  $scope.global_url_test = global_url_front_test;

  $http.get(global_url_test + '/event')
    .then(function (result) {
        $scope.events = result.data;
  });

  $scope.uploadPhotos = function() {
    alert("Quase tudo implementado.  :D ");
    /**
    var url = global_url_test + '/image';

    var config = {
      headers : {
          'Content-Type': 'application/json'
      }
    }    

    var data = new FormData();        
    data.append("file",$scope.file);     
    data.append("photo_path","a");
    $http.post(url, data, config)
      .then(function(response) {
            alert('Evento criado !');
            $location.path("/#");
      });
    **/
  }

  $scope.isInThePast = function(date) {
    return date < new Date();
  }

});

app.filter("reverse", function(){
    return function(items){
        return items.slice().reverse();
    };
});

(function() {
    'use strict';

    angular
        .module('boraApp')
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                .state('viewEvents', {
                    url: '/',
                    templateUrl: 'views/partials/view-events.html',
                    controller: 'ViewEventsController',
                    controllerAs: 'viewEvents'
                })
                .state('createEvent', {
                    url: '/event/create',
                    templateUrl: 'views/partials/create-event.html',
                    controller: 'CreateEventController',
                    controllerAs: 'createEvent'
                })
                .state('viewEvent', {
                    url: '/event/view/:id',
                    templateUrl: 'views/partials/view-event.html',
                    controller: 'ViewEventController',
                    controllerAs: 'viewEvent'
                })

            $urlRouterProvider
                .otherwise('/');
        })
})();