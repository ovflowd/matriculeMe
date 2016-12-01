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

  .controller('GradeCtrl', function($scope,$ionicModal, $state, sugestao) {
    $scope.$on('$ionicView.enter', function(e) {
      var grade = [[{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}],
                   [{},{},{},{},{},{}]];
      for (var i = 0; i < escolhas.length; i++) {
        esc=escolhas[i];
        for (var j = 0; j < esc.turma.horario.length; j++) {
          slot=esc.turma.horario[j];
	  var unfuck = ["06", "08", "10", "12", "14", "16", "18", "20", "22", "24"];
          dias = ["1","2","3","4","5","6"];
          grade[unfuck.indexOf(slot.horarioInicio)][dias.indexOf(slot.dia)]=esc;
        }
      }
      $scope.grade = grade;
    });

    $ionicModal.fromTemplateUrl('pop-up-slot.html', function(modal) {
        $scope.taskModal = modal;
      }, {
        scope: $scope
      }
    );
      
    $scope.mostrarOpcoes = function(disciplina,$index,hora) {
        $scope.hora=hora;
        switch($index){
          case 0: $scope.dia="Segunda-feira";
            break;
          case 1: $scope.dia="Terça-feira";
            break;
          case 2: $scope.dia="Quarta-feira";
            break;
          case 3: $scope.dia="Quinta-feira";
            break;
          case 4: $scope.dia="Sexta-feira";
            break;
          case 5: $scope.dia="Sábado";
            break;
        }
        if(disciplina.codDisc){
          $scope.swap=disciplina;
          $scope.taskModal.show();
        }else{
          $state.go('app.tela6',{'dia':$scope.dia,'hora':$scope.hora});
        }
    };

    $scope.closePopUp = function() {
        $scope.taskModal.hide();
    };

    $scope.irParaTurmas=function(disciplina){
        $state.go("app2.tela5",{"discId": $scope.swap.nomeDisc});
        $scope.taskModal.hide();
    };

    $scope.retirar=function(disciplina){
        filtrado=escolhas.filter(function(elemento){
            return elemento.codDisc != $scope.swap.codDisc;
        });
        escolhas=filtrado;
        $state.reload();
        $scope.taskModal.hide();
    };
	
    $scope.request = function() {
      $scope.oasd = sugestao.gera();
    }
  })

  .controller('EscolhasCtrl', function($scope, $state) {
    $scope.$on('$ionicView.enter', function(e) {
      $scope.escolhas=escolhas;
    });

    $scope.retirar=function(disciplina){
        filtrado=escolhas.filter(function(elemento){
            return elemento.codDisc != disciplina.codDisc;
        });
        escolhas=filtrado;
        $state.reload();
    };
  })

  .controller('AboutUsCtrl', function($scope) {
    
  })

  .controller("BuscaCtrl",function($scope,$http,$ionicPopup,$ionicModal,$state,$stateParams) {
    /* Esta função acessa o servidor e recupera uma lista de disciplinas com nome ou código semelhante a busca.title 
     * Em seguida ele coloca o resultado na lista para serem apresentados
     * Estou supondo que é possivel solicitar com filtro (valor de busca.title) e que o resultado chegue filtrado */
    $scope.msg='todos os horários';
    if($stateParams.dia && $stateParams.hora){
    	$scope.msg=$stateParams.dia+' às '+$stateParams.hora;
    }
    $scope.procurar= function(busca) {
        if(!busca.title){
            return;
        }
        var popUp= $ionicPopup.show({
          title: 'Aguarde',
          subTitle: 'Pesquisando',
          template: '<img src="img/loader.gif" height="42" width="42">'
        });
        $http.get(Url + '/disciplinas/getDisciplina/innome=' + busca.title)
            .success(function (data) {
        $scope.disciplinas = [];
                popUp.close();
                for(i = 0;i < data.length;i++){
                  var fuck = {
                    codDisc:  data[i].codigo,
                    nomeDisc: data[i].nome,
                    dept:     data[i].departamento.sigla,
                    creditos: data[i].credito
                  };
                  $scope.disciplinas.push(fuck);
                }
            })
            .error(function(data,status) {
                popUp.close();
                if(status == '404'){
                  var notice = $ionicPopup.alert({
                    title: 'Não encontrado',
                    template: 'Nenhuma disciplina com este nome, tente novamente'});}
                else {
                    var alertPopup = $ionicPopup.alert({
                        title: 'Erro!',
                        template: 'Ocorreu um erro ao processar a requisição, tente mais tarde'
                    });
                }
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
    };
    
    
    $scope.irParaTurmas=function(disciplina){
        //esqueleto para a função. Redirecionar para a tela 5
        $state.go("app2.tela5",{"discId": $scope.swap.nomeDisc});
        $scope.taskModal.hide();
        //console.log('você selecionou turmas para ',$scope.swap.nomeDisc,'código:',$scope.swap.codDisc);
    };
    
    $scope.addInteresse=function(disciplina){
        //esqueleto para função. Fazer post do código da disciplina e ir para tela 7 (lista de interesses)
        console.log('você adicionou ',$scope.swap.nomeDisc,' a sua lista de interesses');
    };
    
    /* Esta função recupera algumas disciplinas para serem apresentadas na tela quanto esta for aberta
     * Limitar em 10 primeiros resultados 
     * Estou supondo que o seja possivel requisitar apenas 10 resultados */
    //$timeout($scope.procurar({"title":"all"}),1000);
})

  .controller("SugestoesCtrl",function($scope,$http,$ionicPopup,$ionicModal,$state){
    if(!$scope.sugestoes){
        var popUp= $ionicPopup.show({
            title: 'Aguarde',
            subTitle: 'Solicitando lista personalizada',
            template: '<img src="img/loader.gif" height="42" width="42">'
        });
        $http.get('sugestoes.json')
            .success(function (data){
                popUp.close();
                $scope.sugestoes=data.sugestoes;
            })
            .error(function(data) {
                popUp.close();
                var alertPopup = $ionicPopup.alert({
                    title: 'Erro!',
                    template: 'Ocorreu um erro ao processar a requisição, tente mais tarde'
                });
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
        $state.go("app2.tela5",{"discId": $scope.swap.nomeDisc});
        $scope.taskModal.hide();
        //console.log('você selecionou turmas para ',$scope.swap.nomeDisc,'código:',$scope.swap.codDisc);
    };
    
    $scope.desgostar=function(sugestao,$index){
        //console.log('setando prioridade para',$scope.sugestoes[$scope.sugestoes.indexOf(sugestao)].nomeDisc);
	      var posi = $scope.sugestoes.indexOf(sugestao);
        if($scope.sugestoes[posi].prioridade){
            $scope.sugestoes[posi].prioridadeOld=$scope.sugestoes[posi].prioridade;
            $scope.sugestoes[posi].prioridade=0; //Zerar prioridade da sugestão, faz com que a disciplina vá para o fim da lista
            //retirar turma da grade, se houver
            filtrado=escolhas.filter(function(elemento){
                return elemento.codDisc != sugestao.codDisc;
            });
            if(filtrado.length!=escolhas.length){
                var popUp= $ionicPopup.confirm({
                    title: 'Disciplina na grade',
                    template: 'Deseja remover esta disciplina da tua grade?',
                    cancelText: 'Não',
                    okText: 'Sim'
                }).then(function(res){
                    if(res){
                        escolhas=filtrado;
                    }
                });
            }
            document.getElementById('polegar'+$index).className = "button button-icon ion-refresh";
            document.getElementById('polegar'+$index).innerHTML = " Desfazer"
        }else{
          $scope.sugestoes[posi].prioridade=$scope.sugestoes[posi].prioridadeOld;
            document.getElementById('polegar'+$index).className = "button button-icon ion-arrow-down-a";
            document.getElementById('polegar'+$index).innerHTML = " Rejeitar"
        }
    };
      
})

.controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state,$http) {
    $scope.data = {};
    
    var MD5 = function(s){function L(k,d){return(k<<d)|(k>>>(32-d))}function K(G,k){var I,d,F,H,x;F=(G&2147483648);H=(k&2147483648);I=(G&1073741824);d=(k&1073741824);x=(G&1073741823)+(k&1073741823);if(I&d){return(x^2147483648^F^H)}if(I|d){if(x&1073741824){return(x^3221225472^F^H)}else{return(x^1073741824^F^H)}}else{return(x^F^H)}}function r(d,F,k){return(d&F)|((~d)&k)}function q(d,F,k){return(d&k)|(F&(~k))}function p(d,F,k){return(d^F^k)}function n(d,F,k){return(F^(d|(~k)))}function u(G,F,aa,Z,k,H,I){G=K(G,K(K(r(F,aa,Z),k),I));return K(L(G,H),F)}function f(G,F,aa,Z,k,H,I){G=K(G,K(K(q(F,aa,Z),k),I));return K(L(G,H),F)}function D(G,F,aa,Z,k,H,I){G=K(G,K(K(p(F,aa,Z),k),I));return K(L(G,H),F)}function t(G,F,aa,Z,k,H,I){G=K(G,K(K(n(F,aa,Z),k),I));return K(L(G,H),F)}function e(G){var Z;var F=G.length;var x=F+8;var k=(x-(x%64))/64;var I=(k+1)*16;var aa=Array(I-1);var d=0;var H=0;while(H<F){Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=(aa[Z]| (G.charCodeAt(H)<<d));H++}Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=aa[Z]|(128<<d);aa[I-2]=F<<3;aa[I-1]=F>>>29;return aa}function B(x){var k="",F="",G,d;for(d=0;d<=3;d++){G=(x>>>(d*8))&255;F="0"+G.toString(16);k=k+F.substr(F.length-2,2)}return k}function J(k){k=k.replace(/rn/g,"n");var d="";for(var F=0;F<k.length;F++){var x=k.charCodeAt(F);if(x<128){d+=String.fromCharCode(x)}else{if((x>127)&&(x<2048)){d+=String.fromCharCode((x>>6)|192);d+=String.fromCharCode((x&63)|128)}else{d+=String.fromCharCode((x>>12)|224);d+=String.fromCharCode(((x>>6)&63)|128);d+=String.fromCharCode((x&63)|128)}}}return d}var C=Array();var P,h,E,v,g,Y,X,W,V;var S=7,Q=12,N=17,M=22;var A=5,z=9,y=14,w=20;var o=4,m=11,l=16,j=23;var U=6,T=10,R=15,O=21;s=J(s);C=e(s);Y=1732584193;X=4023233417;W=2562383102;V=271733878;for(P=0;P<C.length;P+=16){h=Y;E=X;v=W;g=V;Y=u(Y,X,W,V,C[P+0],S,3614090360);V=u(V,Y,X,W,C[P+1],Q,3905402710);W=u(W,V,Y,X,C[P+2],N,606105819);X=u(X,W,V,Y,C[P+3],M,3250441966);Y=u(Y,X,W,V,C[P+4],S,4118548399);V=u(V,Y,X,W,C[P+5],Q,1200080426);W=u(W,V,Y,X,C[P+6],N,2821735955);X=u(X,W,V,Y,C[P+7],M,4249261313);Y=u(Y,X,W,V,C[P+8],S,1770035416);V=u(V,Y,X,W,C[P+9],Q,2336552879);W=u(W,V,Y,X,C[P+10],N,4294925233);X=u(X,W,V,Y,C[P+11],M,2304563134);Y=u(Y,X,W,V,C[P+12],S,1804603682);V=u(V,Y,X,W,C[P+13],Q,4254626195);W=u(W,V,Y,X,C[P+14],N,2792965006);X=u(X,W,V,Y,C[P+15],M,1236535329);Y=f(Y,X,W,V,C[P+1],A,4129170786);V=f(V,Y,X,W,C[P+6],z,3225465664);W=f(W,V,Y,X,C[P+11],y,643717713);X=f(X,W,V,Y,C[P+0],w,3921069994);Y=f(Y,X,W,V,C[P+5],A,3593408605);V=f(V,Y,X,W,C[P+10],z,38016083);W=f(W,V,Y,X,C[P+15],y,3634488961);X=f(X,W,V,Y,C[P+4],w,3889429448);Y=f(Y,X,W,V,C[P+9],A,568446438);V=f(V,Y,X,W,C[P+14],z,3275163606);W=f(W,V,Y,X,C[P+3],y,4107603335);X=f(X,W,V,Y,C[P+8],w,1163531501);Y=f(Y,X,W,V,C[P+13],A,2850285829);V=f(V,Y,X,W,C[P+2],z,4243563512);W=f(W,V,Y,X,C[P+7],y,1735328473);X=f(X,W,V,Y,C[P+12],w,2368359562);Y=D(Y,X,W,V,C[P+5],o,4294588738);V=D(V,Y,X,W,C[P+8],m,2272392833);W=D(W,V,Y,X,C[P+11],l,1839030562);X=D(X,W,V,Y,C[P+14],j,4259657740);Y=D(Y,X,W,V,C[P+1],o,2763975236);V=D(V,Y,X,W,C[P+4],m,1272893353);W=D(W,V,Y,X,C[P+7],l,4139469664);X=D(X,W,V,Y,C[P+10],j,3200236656);Y=D(Y,X,W,V,C[P+13],o,681279174);V=D(V,Y,X,W,C[P+0],m,3936430074);W=D(W,V,Y,X,C[P+3],l,3572445317);X=D(X,W,V,Y,C[P+6],j,76029189);Y=D(Y,X,W,V,C[P+9],o,3654602809);V=D(V,Y,X,W,C[P+12],m,3873151461);W=D(W,V,Y,X,C[P+15],l,530742520);X=D(X,W,V,Y,C[P+2],j,3299628645);Y=t(Y,X,W,V,C[P+0],U,4096336452);V=t(V,Y,X,W,C[P+7],T,1126891415);W=t(W,V,Y,X,C[P+14],R,2878612391);X=t(X,W,V,Y,C[P+5],O,4237533241);Y=t(Y,X,W,V,C[P+12],U,1700485571);V=t(V,Y,X,W,C[P+3],T,2399980690);W=t(W,V,Y,X,C[P+10],R,4293915773);X=t(X,W,V,Y,C[P+1],O,2240044497);Y=t(Y,X,W,V,C[P+8],U,1873313359);V=t(V,Y,X,W,C[P+15],T,4264355552);W=t(W,V,Y,X,C[P+6],R,2734768916);X=t(X,W,V,Y,C[P+13],O,1309151649);Y=t(Y,X,W,V,C[P+4],U,4149444226);V=t(V,Y,X,W,C[P+11],T,3174756917);W=t(W,V,Y,X,C[P+2],R,718787259);X=t(X,W,V,Y,C[P+9],O,3951481745);Y=K(Y,h);X=K(X,E);W=K(W,v);V=K(V,g)}var i=B(Y)+B(X)+B(W)+B(V);return i.toLowerCase()};


    $scope.signup = function(){
		  $state.go('signup');
    }

    $scope.login = function() {
	//Modificação do danilo: "Apenas deixei como comentário para teste no mobile!	    
       var result = (MD5($scope.data.password));
       $scope.data.senha = result;
       var popUp= $ionicPopup.show({
          title: 'Aguarde',
          subTitle: 'Fazendo Login no Servidor',
          template: '<img src="img/loader.gif" height="42" width="42">'
       });
       $http.get(Url+'/alunos/getAluno/login='+$scope.data.username+'&senha='+$scope.data.senha).success(function(data) {
            popUp.close();
            $state.go('app.grade');
         }).error(function(data) {
             popUp.close();
             var alertPopup = $ionicPopup.alert({
                 title: 'Falha no login!',
                 template: 'Por favor  cheque seus dados!'
             });
         });
    }
})

.controller('SignupCtrl',function($scope, SignupService, $ionicPopup,$state,$http,$ionicModal) {
  $scope.data = {};

  var MD5 = function(s){function L(k,d){return(k<<d)|(k>>>(32-d))}function K(G,k){var I,d,F,H,x;F=(G&2147483648);H=(k&2147483648);I=(G&1073741824);d=(k&1073741824);x=(G&1073741823)+(k&1073741823);if(I&d){return(x^2147483648^F^H)}if(I|d){if(x&1073741824){return(x^3221225472^F^H)}else{return(x^1073741824^F^H)}}else{return(x^F^H)}}function r(d,F,k){return(d&F)|((~d)&k)}function q(d,F,k){return(d&k)|(F&(~k))}function p(d,F,k){return(d^F^k)}function n(d,F,k){return(F^(d|(~k)))}function u(G,F,aa,Z,k,H,I){G=K(G,K(K(r(F,aa,Z),k),I));return K(L(G,H),F)}function f(G,F,aa,Z,k,H,I){G=K(G,K(K(q(F,aa,Z),k),I));return K(L(G,H),F)}function D(G,F,aa,Z,k,H,I){G=K(G,K(K(p(F,aa,Z),k),I));return K(L(G,H),F)}function t(G,F,aa,Z,k,H,I){G=K(G,K(K(n(F,aa,Z),k),I));return K(L(G,H),F)}function e(G){var Z;var F=G.length;var x=F+8;var k=(x-(x%64))/64;var I=(k+1)*16;var aa=Array(I-1);var d=0;var H=0;while(H<F){Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=(aa[Z]| (G.charCodeAt(H)<<d));H++}Z=(H-(H%4))/4;d=(H%4)*8;aa[Z]=aa[Z]|(128<<d);aa[I-2]=F<<3;aa[I-1]=F>>>29;return aa}function B(x){var k="",F="",G,d;for(d=0;d<=3;d++){G=(x>>>(d*8))&255;F="0"+G.toString(16);k=k+F.substr(F.length-2,2)}return k}function J(k){k=k.replace(/rn/g,"n");var d="";for(var F=0;F<k.length;F++){var x=k.charCodeAt(F);if(x<128){d+=String.fromCharCode(x)}else{if((x>127)&&(x<2048)){d+=String.fromCharCode((x>>6)|192);d+=String.fromCharCode((x&63)|128)}else{d+=String.fromCharCode((x>>12)|224);d+=String.fromCharCode(((x>>6)&63)|128);d+=String.fromCharCode((x&63)|128)}}}return d}var C=Array();var P,h,E,v,g,Y,X,W,V;var S=7,Q=12,N=17,M=22;var A=5,z=9,y=14,w=20;var o=4,m=11,l=16,j=23;var U=6,T=10,R=15,O=21;s=J(s);C=e(s);Y=1732584193;X=4023233417;W=2562383102;V=271733878;for(P=0;P<C.length;P+=16){h=Y;E=X;v=W;g=V;Y=u(Y,X,W,V,C[P+0],S,3614090360);V=u(V,Y,X,W,C[P+1],Q,3905402710);W=u(W,V,Y,X,C[P+2],N,606105819);X=u(X,W,V,Y,C[P+3],M,3250441966);Y=u(Y,X,W,V,C[P+4],S,4118548399);V=u(V,Y,X,W,C[P+5],Q,1200080426);W=u(W,V,Y,X,C[P+6],N,2821735955);X=u(X,W,V,Y,C[P+7],M,4249261313);Y=u(Y,X,W,V,C[P+8],S,1770035416);V=u(V,Y,X,W,C[P+9],Q,2336552879);W=u(W,V,Y,X,C[P+10],N,4294925233);X=u(X,W,V,Y,C[P+11],M,2304563134);Y=u(Y,X,W,V,C[P+12],S,1804603682);V=u(V,Y,X,W,C[P+13],Q,4254626195);W=u(W,V,Y,X,C[P+14],N,2792965006);X=u(X,W,V,Y,C[P+15],M,1236535329);Y=f(Y,X,W,V,C[P+1],A,4129170786);V=f(V,Y,X,W,C[P+6],z,3225465664);W=f(W,V,Y,X,C[P+11],y,643717713);X=f(X,W,V,Y,C[P+0],w,3921069994);Y=f(Y,X,W,V,C[P+5],A,3593408605);V=f(V,Y,X,W,C[P+10],z,38016083);W=f(W,V,Y,X,C[P+15],y,3634488961);X=f(X,W,V,Y,C[P+4],w,3889429448);Y=f(Y,X,W,V,C[P+9],A,568446438);V=f(V,Y,X,W,C[P+14],z,3275163606);W=f(W,V,Y,X,C[P+3],y,4107603335);X=f(X,W,V,Y,C[P+8],w,1163531501);Y=f(Y,X,W,V,C[P+13],A,2850285829);V=f(V,Y,X,W,C[P+2],z,4243563512);W=f(W,V,Y,X,C[P+7],y,1735328473);X=f(X,W,V,Y,C[P+12],w,2368359562);Y=D(Y,X,W,V,C[P+5],o,4294588738);V=D(V,Y,X,W,C[P+8],m,2272392833);W=D(W,V,Y,X,C[P+11],l,1839030562);X=D(X,W,V,Y,C[P+14],j,4259657740);Y=D(Y,X,W,V,C[P+1],o,2763975236);V=D(V,Y,X,W,C[P+4],m,1272893353);W=D(W,V,Y,X,C[P+7],l,4139469664);X=D(X,W,V,Y,C[P+10],j,3200236656);Y=D(Y,X,W,V,C[P+13],o,681279174);V=D(V,Y,X,W,C[P+0],m,3936430074);W=D(W,V,Y,X,C[P+3],l,3572445317);X=D(X,W,V,Y,C[P+6],j,76029189);Y=D(Y,X,W,V,C[P+9],o,3654602809);V=D(V,Y,X,W,C[P+12],m,3873151461);W=D(W,V,Y,X,C[P+15],l,530742520);X=D(X,W,V,Y,C[P+2],j,3299628645);Y=t(Y,X,W,V,C[P+0],U,4096336452);V=t(V,Y,X,W,C[P+7],T,1126891415);W=t(W,V,Y,X,C[P+14],R,2878612391);X=t(X,W,V,Y,C[P+5],O,4237533241);Y=t(Y,X,W,V,C[P+12],U,1700485571);V=t(V,Y,X,W,C[P+3],T,2399980690);W=t(W,V,Y,X,C[P+10],R,4293915773);X=t(X,W,V,Y,C[P+1],O,2240044497);Y=t(Y,X,W,V,C[P+8],U,1873313359);V=t(V,Y,X,W,C[P+15],T,4264355552);W=t(W,V,Y,X,C[P+6],R,2734768916);X=t(X,W,V,Y,C[P+13],O,1309151649);Y=t(Y,X,W,V,C[P+4],U,4149444226);V=t(V,Y,X,W,C[P+11],T,3174756917);W=t(W,V,Y,X,C[P+2],R,718787259);X=t(X,W,V,Y,C[P+9],O,3951481745);Y=K(Y,h);X=K(X,E);W=K(W,v);V=K(V,g)}var i=B(Y)+B(X)+B(W)+B(V);return i.toLowerCase()};
	
  $scope.submit = function(){
    if(!($scope.data.first_name == "" || $scope.data.matricula.length != 9 || $scope.data.login == "" || $scope.data.password.length < 6 || $scope.data.password != $scope.data.password2)){
    var result = (MD5($scope.data.password));
    $scope.data.senha = result;
    var popUp= $ionicPopup.show({
          title: 'Aguarde',
          subTitle: 'Fazendo Seu Registro no Servidor',
          template: '<img src="img/loader.gif" height="42" width="42">'
    });
    var config = { headers:{
        'Content-Type' : 'text/plain'
      }
    }
    var send = {
        nome:      $scope.data.first_name,
        matricula: $scope.data.matricula,
        login: {
          accessKey: $scope.data.login,
          password:  $scope.data.senha
        }
      }
    $http.post(Url+'/alunos/setAluno/',send,config
    ).success(function(response) {
      popUp.close();
      var notice = $ionicPopup.alert({
        title: 'Cadastro  realizado!',
        template: 'Conta criada com sucesso'});
      $state.go('login');
    }).error(function(response,status) {
      popUp.close();
      if(status == '483'){
      var notice = $ionicPopup.alert({
        title: 'Falha no cadastro!',
        template: 'Conta já existente.'});}
       else {
         var notice = $ionicPopup.alert({
           title: 'Erro!',
           template: 'Servidor não responde. Tente novamente mais tarde.'
         });
      }
    });
  }}
  $scope.voltar=function(){
        $state.go('login');
  }
  
  $ionicModal.fromTemplateUrl('termosdeuso.html', function(modal) {
      $scope.taskModal = modal;
      }, {
        scope: $scope
      }
    );
      
  $scope.mostrarTermo = function(sugestao,index) {
      $scope.taskModal.show();
  };

  $scope.closePopUp = function() {
      $scope.taskModal.hide();
  };
})

.controller("TurmasCtrl",function($scope,$http,$stateParams,$state,$ionicPopup){
    //este controlador deve receber a disciplina para a qual se deseja selecionar uma turma como parâmetro
    //Solicita lista de turmas da disciplina para o servidor
    //Usar a id em $stateParams.discId para selecionar turmas da disciplina certa
    console.log($stateParams.discId);
    var popUp= $ionicPopup.show({
          title: 'Aguarde',
          subTitle: 'Recuperando turmas da base de dados',
          template: '<img src="img/loader.gif" height="42" width="42">'
    });
    $http.get(Url + '/turmas/getTurmas/disciplina=' + $stateParams.discId)
        .success(function (data){
	    var fuck = {
          	codDisc: data[0].oferta.disciplina.codigo,
          	nomeDisc: data[0].oferta.disciplina.nome,
          	turmas: []
            };
	    for (i = 0; i < data.length; i++) {
          	var turma = {codTurma: data[i].codigo, nomeProf: data[i].professor.nome, horario: data[i].horario};
          	fuck.turmas.push(turma);
            }
            popUp.close();
            $scope.disciplina = fuck;
        })
        .error(function(data,status) {
            popUp.close();
            if(status == '404'){
                  var notice = $ionicPopup.alert({
                    title: 'Não encontrado',
                    template: 'Nenhuma turma para esta disciplina'
                });
            }else{
                var alertPopup = $ionicPopup.alert({
                    title: 'Erro!',
                    template: 'Ocorreu um erro ao processar a requisição, tente mais tarde'
                });
            }
        });
    
    $scope.addGrade=function(turma){
        //em caso de mudança de turma para uma mesma disciplina, remove-a
        filtrado=escolhas.filter(function(elemento){
          return (elemento.codDisc != $scope.disciplina.codDisc);
        });
        //verificar a grade do usuário quanto a conflitos de horário
        //@TODO Este filtro necessita ser testado no mundo real.
        ocupado=filtrado.filter(function(elemento){
            return (elemento.horario.dia == turma.horario.dia) && ((elemento.horario.horaIni >= turma.horario.horaIni && elemento.horario.horaIni < turma.horario.horaFim) || (elemento.horario.horaFim > turma.horario.horaIni && elemento.horario.horaFim <= turma.horario.horaFim));
        });
        //alerta-o quanto a mudança
        if(ocupado.length){
            var texto='<p>Você já possui disciplinas nestes horários </p><p> Deseja retirar a(s) disciplina(s)? </p>';
            for(disc in ocupado){
                texto+='<p>'+ocupado.nomeDisc+'</p>';
            }
            $ionicPopup.confirm({
                title: 'Grade Ocupada',
                template: texto,
                cancelText: 'Manter como está',
                okText: 'Remover esta(s) disciplina(s)'
            }).then(function(res){
                if(res){
                    //remove disciplinas conflitantes
                    for(disc in ocupado){
                        filtrado.splice(filtrado.indexOf(disc),1);
                    }
                }else {
                    //sai sem fazer alterações
                    return;
                }
            });
        }//else
        //Insere a nova escolha à lista de escolhas do usuário
        filtrado.push({
            codDisc: $scope.disciplina.codDisc,
            nomeDisc: $scope.disciplina.nomeDisc,
            turma: turma
        });
        escolhas=filtrado;
        console.log('A disciplina',$scope.disciplina.nomeDisc,'turma',turma.codTurma,'foi adicionada a tua grade');
        //o ideal é retornar para a grade, mas tela3 é boa para testes
        $state.transitionTo('app.grade', {}, { 
            location: true, inherit: true, relative: 'app.grade', notify: true, reload: true
        });
    }
})

.controller('WebCtrl', function($scope, $state, $timeout,$ionicPopup, $http) {
    $scope.saida="Contactando MatriculaWeb";
    var timer=null;
    var enviar={};
    var count=0;
    var x = document.getElementById("oi");
    //x.style.display='none';
    //Abrir a página de login
    var frame=window.frames[0];
    $scope.$on('$ionicView.enter', function(e) {
        frame.location="https://wwwsec.serverweb.unb.br/graduacao/sec/login.aspx?sair=1";
        enviar={htmlHist:'',htmlQR:''};
    });

    //Função que automatiza a captura e navegação
    verifica=function(){
      count++;
      if(count>5){
        clearInterval(timer);
        var popUp= $ionicPopup.confirm({
            title: 'Fora do ar!?',
            subTitle: 'O MatriculaWeb está demorando muito',
            template: 'O que desejas fazer?',
            cancelText: 'Aguardar',
            okText: 'Tentar mais tarde'
        }).then(function(res){
            if(res){
                $state.go('app.grade');
            }else{
                count=0;
                timer=setInterval(verifica,3000);
            }
        });
      }
      //Obtem o conteúdo (document) do iframe
      var x = document.getElementById("oi");
      //x.style.display='none';
      var y = (x.contentWindow || x.contentDocument);
      if (y.document) y = y.document; //Access denied! (no browser, via emulador rola de boa)
      //Verifica se está na página de login
      var qtd=y.getElementsByName("inputMatricula").length;
      if (qtd){
        //Se estiver na página de login, a faz aparecer
        x.style.display='block';
        count=0;
      }else{
        //Se não for a página de login, verifica se o histórico está aberto
        if(y.getElementById("alumatricula") != null){
          enviar.htmlHist=y.body.innerHTML;
          //alert(enviar.htmlHist);
        }
        if(y.getElementById("lblAlumatricula") != null){
          enviar.htmlQR=y.body.innerHTML;
          //alert(enviar.htmlQR);
        }
        if (enviar.htmlHist && enviar.htmlQR){
          //Se for o histórico, captura e envia, muda de estado
          var config = { headers:{
              'Content-Type' : 'text/plain'
            }
          }
          clearInterval(timer);
          $http.post(Url+'/disciplinasCursadas/setHist',enviar,config)
                  .success(function(response){
                      var notice = $ionicPopup.alert({
                      title: 'Obrigado',
                      template: 'Agora podemos gerar sugestões para você.'});
                      $state.go('app.grade');
                  }).error(function(response){
                    var popUp= $ionicPopup.confirm({
                        title: 'Fora do ar!?',
                        subTitle: 'Não foi possivel conectar com nosso servidor',    
                        template: 'O que deseja fazer?',
                        cancelText: 'Tentar novamente',
                        okText: 'Tentar mais tarde'
                      }).then(function(res){    
                        if(res){
                            $state.go('app.grade');
                        }else{
                            count=0;
                            timer=setInterval(verifica,3000);
                        }
                    });
                  });
        }else{
          //Se não for a página do histórico, verifica se o aluno está logado
          qtd=y.getElementsByClassName("PadraoMenu").length;
          if (qtd>1){
            //Se estiver logado, abre a página do histórico
            $scope.saida="Obtendo teu histórico, aguarde"
            if(!enviar.htmlHist){
              frame.location="https://wwwsec.serverweb.unb.br/graduacao/sec/he.aspx";
            }else{
              frame.location="https://wwwsec.serverweb.unb.br/graduacao/sec/qr.aspx";
            }
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
