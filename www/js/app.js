// Ionic Starter App
var grade = [[{},{},{},{},{},{}],[{},{},{},{},{},{}],[{},{},{},{},{},{}],[{},{},{},{},{},{}],[{},{},{},{},{},{}],[{},{},{},{},{},{}],[{},{},{},{},{},{}]];
var escolhas=[]; //Variável global que registra as escolhas do usuário para tela3 ("suas escolhas")
// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers','starter.services'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

	.state('login', {
      url: '/login',
      templateUrl: 'templates/login.html',
	  controller: 'LoginCtrl'
  })

	.state('signup', {
      url: '/signup',
      templateUrl: 'templates/signup.html',
	    controller: 'SignupCtrl'
  })

    .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })
  
  .state('app2', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })

  .state('app.grade', {
    url: '/grade',
    views: {
      'menuContent': {
        templateUrl: 'templates/grade.html',
        controller: 'GradeCtrl'
      }
    }
  })

  .state('app.tela4', {
      url: '/tela4',
      views: {
        'menuContent': {
          templateUrl: 'templates/tela4.html',
          controller: 'Tela4Ctrl'
        }
      }
    })

  .state('app2.tela5', {
      url: '/tela5/:discId',
      views: {
        'menuContent': {
          templateUrl: 'templates/turmas.html',
          controller: 'Tela5Ctrl'
        }
      }
  })
  
  .state('app.tela3', {
      url: '/tela3',
      views: {
        'menuContent': {
          templateUrl: 'templates/tela3.html',
          controller: 'Tela3Ctrl'
        }
      }
  })
    
    .state('app.tela6', {
      url: '/tela6',
      views: {
        'menuContent': {
          templateUrl: 'templates/tela6.html',
          controller: 'Tela6Ctrl'
        }
      }
    })

    .state('captura', {
        url: '/captura',
        templateUrl: 'templates/webview.html',
        controller: 'WebCtrl'
      }
    )

  .state('app.single', {
    url: '/playlists/:playlistId',
    views: {
      'menuContent': {
        templateUrl: 'templates/playlist.html',
        controller: 'PlaylistCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');
});
