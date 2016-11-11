angular.module('starter.controllers', [])

.controller("mostrar",function($scope,$http,$ionicModal){
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

.controller("buscar",function($scope,$http,$timeout,$ionicModal) {
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
    $timeout($scope.procurar({"title":"all"}),1000);
    
});
    
    
