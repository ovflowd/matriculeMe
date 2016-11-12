![](https://github.com/sant0ro/matriculeMe/raw/master/Logo.png)
#### Embedded Solution for University of Brasília Registration System
-------------------------------------

<b>O que é o matriculeMe?</b><br>
Uma solução criada para realizar sugestões de disciplinas e montagem de uma grade horária otimizada e personalizada para o aluno que o utilizar. Usando mecanismos de inferência e aprendizagem de máquina e a base de dados do MatriculaWEB em conjunto com seu histórico escolar (UnB) para organizar e sugerir uma grade horária otimizada para seu semestre.

<b>Qual o conteúdo desta Branch?</b><br>
Arquivos relacionados ao protótipo do **Servidor** do **matriculeMe**

<b>Como foi Desenvolvido?</b><br>
O Servidor (Núcleo) do **matriculeMe** foi desenvolvido com a linguagem Java e utilizando as seguintes bibliotecas:

* Hibernate
* Jersey
* Apache Tomcat
* Entre outros.

<b>Como é constituido a comunicação entre Servidor <> App Mobile?</b><br>
A comunicação acontece através do protocolo `HTTP` e com requisições utilizando a base `RESTful`. 
Além disso o Servidor utiliza pacotes de dados em `jSON` para a interpretação e comunicação dos dados.

Você poderá ver a **API de Documentação**, através do [Swagger Editor](https://editor.swagger.io) 
utilizando a nossa API disponível [aqui](https://raw.githubusercontent.com/sant0ro/matriculeMe/servidor-java/Api/Swagger.yaml)

Você também poderá baixar o pacote do _Swagger UI_ disponível na pasta [docs](https://github.com/sant0ro/matriculeMe/tree/servidor-java/Api/Docs)
onde poderá visualizar a Documentação através de uma interface gráfica.

<b>Segurança do Aplicativo</b><br>
A proposta de segurança é através de autenticação por um **Token** de segurança em SHA256 de 24 caractéres de comprimento.
A proposta será ainda atualizada o sufucientemente.

<b>Principal Função do Servidor</b><br>
Realizar o gerenciamento dos dados do **matriculeMe** e comunicação/interação com os módulos de Machine Learning, 
Interação com MatriculaWEB e Aplicativo Mobile.

<b>Licença e Uso</b><br>
O Aplicativo Mobile do **matriculeMe** é Open Source e sob a Licença MIT. 

* Qualquer um poderá utilizar ou contribuir. Verifique a lista de Bugs e To-Do's atuais, acessando a Página de Issues. Clicando [aqui](https://github.com/sant0ro/matriculeMe/issues). 
* Você também poderá realizar Pull Requests clicando [aqui](https://github.com/sant0ro/matriculeMe/pulls).
* Contribua com a nossa página Wiki, escrevendo informações e guias úteis. Clicando [aqui](https://github.com/sant0ro/matriculeMe/wiki).
