angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $ionicModal, $timeout) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})

  .controller('GradeCtrl', function($scope, sugestao) {
    $scope.oasd = grade[0];
    $scope.dasm = grade[1];
    $scope.masq = grade[2];
    $scope.qads = grade[3];
    $scope.dsado = grade[4];
    $scope.doav = grade[5];
    $scope.vavd = grade[6];

    $scope.request = function() {
      $scope.oasd = sugestao.gera();
    }
  })

  .controller("Tela6Ctrl",function($scope,$http,$timeout,$ionicModal) {
    /* Esta função acessa o servidor e recupera uma lista de disciplinas com nome ou código semelhante a busca.title
     * Em seguida ele coloca o resultado na lista para serem apresentados
     * Estou supondo que é possivel solicitar com filtro (valor de busca.title) e que o resultado chegue filtrado */
    $scope.procurar= function(busca) {
      if(!busca.title){
        return;
      }
      $http.get('http://172.16.5.26:8080/mprjct3/alunos/getAlunos/nome=Nicolas')
        .success(function (data) {
          $scope.disciplinas=data.nome;
        });
    }

    $ionicModal.fromTemplateUrl('pop-up-info.html', function(modal) {
        $scope.taskModal = modal;
      }, {
        scope: $scope
      }
    );

    $scope.mostrarInfo = function(disciplina,index) {
      $scope.swap=disciplina;
      $scope.taskModal.show();
    };

    $scope.closePopUp = function() {
      $scope.taskModal.hide();
    }


    $scope.irParaTurmas=function(disciplina){
      //esqueleto para a função. Redirecionar para a tela 5 (mostra turmas)
      console.log('você selecionou turmas para ',$scope.swap.nomeDisc);
    }

    $scope.addInteresse=function(disciplina){
      //esqueleto para função. Fazer post do código da disciplina e ir para tela 7 (lista de interesses)
      console.log('você adicionou ',$scope.swap.nomeDisc,' a sua lista de interesses');
    }

    /* Esta função recupera algumas disciplinas para serem apresentadas na tela quanto esta for aberta
     * Limitar em 10 primeiros resultados
     * Estou supondo que o seja possivel requisitar apenas 10 resultados */
    $timeout($scope.procurar({"title":"all"}),1000);

  })

  .controller('Tela4Ctrl',function($scope,$http,$ionicModal){
    $http.get('sugestoes.json')
      .success(function (data){
        $scope.sugestoes=data.sugestoes;
        $scope.sugestoes.sort();
      });

    $ionicModal.fromTemplateUrl('pop-up-motivo.html', function(modal) {
      $scope.taskModal = modal;
    }, {
      scope: $scope
    });

    $scope.mostraMotivo = function(sugestao,index) {
      //não encontrei foma mais simples de fazer isso funcionar. Variáveis somente são apresentadas se forem parte de $scope, então criei esta swap que pode ajudar em vários contextos parecidos
      $scope.swap=sugestao;
      $scope.taskModal.show();
    };

    $scope.closePopUp = function() {
      $scope.taskModal.hide();
    }

  })

.controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state) {
    $scope.data = {};

    $scope.signup = function(){
		  $state.go('signup');
    }

    $scope.login = function() {
        LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            $state.go('app.grade');
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
    }
})

.controller('SignupCtrl',function($scope, SignupService, $ionicPopup,$state) {
  $scope.data = {};

  $scope.voltar = function(){
      $state.go('login');
    }
  
  $ionicModal.fromTemplateUrl('termosdeuso.html', function(modal) {
        $scope.taskModal = modal;
      }, {
        scope: $scope
      }
    );  

  $scope.mostrarTermo = function(){
      $scope.taskModal.show();
    }  
  $scope.closePopUp = function() {
      $scope.taskModal.hide();
    }  

  $scope.submit = function(){
    SignupService.signupUser($scope.data.login,$scope.data.password).success(function(data) {
      var notice = $ionicPopup.alert({
        
        title: 'Sucesso no Cadastro',
        template: 'Conta criada.'});
      $state.go('login');
    }).error(function(data) {
      var notice = $ionicPopup.alert({
        title: 'Falha no Cadastro',
        template: 'Conta já existente.'});
    });
  }
})


  .controller('PlaylistsCtrl', function($scope) {
  $scope.playlists = [
    { title: 'Reggae', id: 1 },
    { title: 'Chill', id: 2 },
    { title: 'Dubstep', id: 3 },
    { title: 'Indie', id: 4 },
    { title: 'Rap', id: 5 },
    { title: 'Cowbell', id: 6 }
  ];
})

.controller('PlaylistCtrl', function($scope, $stateParams) {
});
