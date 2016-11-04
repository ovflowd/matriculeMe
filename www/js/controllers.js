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
        $http.get('disciplinas.json')
            .success(function (data) {
                $scope.disciplinas=data.disciplinas;
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
    //$timeout($scope.procurar({"title":"all"}),1000);
})

  .controller("Tela4Ctrl",function($scope,$http,$ionicModal){
    if(!$scope.sugestoes){
        $http.get('sugestoes.json')
            .success(function (data){
                $scope.sugestoes=data.sugestoes;
            });
    }
        
    $ionicModal.fromTemplateUrl('pop-up-motivo.html', function(modal) {
        $scope.taskModal = modal;
      }, {
        scope: $scope
      }
    );
      
    $scope.mostraMotivo = function(sugestao,index) {
        //não encontrei foma mais simples de fazer isso funcionar. Variáveis somente são apresentadas se forem parte de $scope, então criei esta swap que pode ajudar em vários contextos parecidos
        $scope.swap=sugestao;
        $scope.taskModal.show();
    };

    $scope.closePopUp = function() {
        $scope.taskModal.hide();
    };
    
    $scope.irParaTurmas=function(disciplina){
        //esqueleto para a função. Redirecionar para a tela 5
        console.log('você selecionou turmas para ',$scope.swap.nomeDisc);
    };
    
    $scope.desgostar=function(sugestao,$index){
        console.log('setando prioridade para',$scope.sugestoes[$scope.sugestoes.indexOf(sugestao)].nomeDisc);
        $scope.sugestoes[$scope.sugestoes.indexOf(sugestao)].prioridade=0; //Zerar prioridade da sugestão, faz com que a disciplina vá para o fim da lista
        //retirar turma da grade, se houver
    };
      
})

.controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state,$http) {
    $scope.data = {};

    $scope.signup = function(){
		  $state.go('signup');
    }

    $scope.login = function() {
        $http.get('http://172.16.5.81:8080/mprjct3/alunos/getAlunos/nome='+$scope.data.username+'&senha='+$scope.data.password
        ).success(function(data) {
            $state.go('app.grade');
        }).error(function(data) {
            var alertPopup = $ionicPopup.alert({
                title: 'Login failed!',
                template: 'Please check your credentials!'
            });
        });
    }
})

.controller('SignupCtrl',function($scope, SignupService, $ionicPopup,$state,$http) {
  $scope.data = {};

  $scope.submit = function(){
    var config = { headers:{
        'Content-Type' : 'text/plain'
      }
    }
    $http.post('http://172.16.5.81:8080/mprjct3/alunos/setAlunos/',$scope.data,config
    ).success(function(response) {
      var notice = $ionicPopup.alert({
        title: 'Signup success!',
        template: 'Account created'});
      $state.go('login');
    }).error(function(response,status) {
      if(status == '483'){
      var notice = $ionicPopup.alert({
        title: 'Signup failed!',
        template: 'Account already exist'});}
       else {
         var notice = $ionicPopup.alert({
           title: 'ERROR',
           template: 'Server ERROR'
         });
      }
    });
  }
})


  .controller('WebCtrl', function($scope, $stateParams, $timeout) {
    $scope.saida="contactando MatriculaWeb";
    var timer=null;
    var x = document.getElementById("oi");
    x.style.display='none';
    //Abrir a página de login
    var frame=window.frames[0];
    frame.location="http://wwwsec.serverweb.unb.br/graduacao/sec/login.aspx";

    //Função que automatiza a captura e navegação
    verifica=function(){
      //Obtem o conteúdo (document) do iframe
      var x = document.getElementById("oi");
      x.style.display='none';
      var y = (x.contentWindow || x.contentDocument);
      if (y.document) y = y.document; //Access denied! (no browser, via emulador rola de boa)
      //Verifica se está na página de login
      var qtd=y.getElementsByName("inputMatricula").length;
      if (qtd){
        //Se estiver na página de login, a faz aparecer
        x.style.display='block';
      }else{
        //Se não for a página de login, verifica se o histórico está aberto
        if (y.getElementById("alumatricula") != null){
          //Se for o histórico, captura e envia, muda de estado
          //Verificar se o envio do HTML via $http funciona
          var config = { headers:{
              'Content-Type' : 'text/plain'
            }
          }
          $http.post('172.16.5.81:8080/mprjct3/historico/setHist',y.body.innerHTML,config);
          //por alguma razão o echo não acontece imediatamente
          //$scope.saida=y.body.innerHTML;//echo
          //O alert abaixo funciona, logo esta parte da função é executada em algum momento
          alert(y.body.innerHTML);
          clearInterval(timer);
          $state.go("/app/grade");
        }else{
          //Se não for a página do histórico, verifica se o aluno está logado
          qtd=y.getElementsByClassName("PadraoMenu").length;
          if (qtd>1){
            //Se estiver logado, abre a página do histórico
            frame.location="https://wwwsec.serverweb.unb.br/graduacao/sec/he.aspx";
          }else{
            //Se não estiver logado, direciona para a página de login
            frame.location="http://wwwsec.serverweb.unb.br/graduacao/sec/login.aspx";
          }
        }
      }
    };

    //Faz verificações a cada ~3 segundos. O ideal seria usar "onload" ou "document.ready" mas não estavam se comportando como esperado
    timer=setInterval(verifica,3000);

  });
