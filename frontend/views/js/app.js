var app = angular.module('boraApp', []);

app.controller('ViewEventController', function ViewEventController($scope) {

  $scope.event = {"date": new Date(2017, 1, 19, 16, 30),
                  "name": "Feijoada do Hackeie o LSD",
                  "local": "LSD",
                  "people": ["Arnett", "Manel", "Chico", "Esdras", "Igor", "Fubica", "Raquel"]};

});

app.controller('CreateEventController', function CreateEventController($scope) {

  $scope.newEvent = {}

  $scope.showEvent = function() {
    console.log($scope.newEvent)
  }

});

app.controller('ViewEventsController', function ViewEventsController($scope) {

  $scope.events = [{"date": new Date(2017, 4, 6, 12, 30),
                    "name": "Feijoada do Hackeie o LSD",
                    "local": "LSD",
                    "people": ["Arnett", "Manel", "Chico", "Esdras", "Igor", "Fubica", "Raquel"]
                   },

                   {"date": new Date(2016, 0, 1, 20, 00),
                    "name": "Fogbeer",
                    "local": "Nilson",
                    "people": ["Fulano", "Sicrano", "Minino", "Whynderssonn", "Joao", "Foo", "Bar"]
                   }];

  $scope.isInThePast = function(date) {
    console.log(date)
    console.log(new Date())
    console.log(date < new Date())
    return date < new Date();
  }

});