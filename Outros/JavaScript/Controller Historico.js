angular.module('starter.controllers', [])

.controller('WebCtrl', function($scope, $stateParams, $timeout) {
    $scope.saida="vazio";
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
                //$http.post(y.body.innerHTML);
                //por alguma razão o echo não acontece imediatamente
                //$scope.saida=y.body.innerHTML;//echo
                //O alert abaixo funciona, logo esta parte da função é executada em algum momento
                //alert(y.body.innerHTML);
                clearInterval(timer);
                //$state.go(#/tela4);
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
