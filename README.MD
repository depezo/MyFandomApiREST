# Aplicativo de fandom
### Objetivo do aplicativo:
   O aplicativo de fandom permite gerenciar uma fandom de anime, ou mangá. Nela é possível se obter informações sobre animes, autores, personagens, fazer comentários, administrar usuários etc.

## Objetivo do projeto:
 - Implementar uma api para aprendizado e consolidação das seguintes tecnologias e conceitos: 
 - Spring Boot Core
 - Spring Boot Web Module
 - Spring Boot Data Module
 - Spring Security Module
 - Controle de acesso
 - Controle de permissões
 - Validação de dados
 - Java persistence API (JPA)
 - Rest e RestFul
 - Aplicação dividida em camadas: persistence, service, domain, application.
 - Testes de unidade e integração básico
 - Cobertura de código básico
 - Aprender o Swagger para documentação de api.
 - Operação com erros e exceptions.

### Entidades Importantes:
 - Anime
 - Categoria
 - Personagem
 - Comentários sobre anime e personagem.
 - Usuário/Administrador
 - Permissões
 - Roles


# Controllers:
 - ### /usuario
 -  [post]/usuario/cadastrar
 -  [post]/usuario/logar
 -  [post]/usuario/pegar_token
 -  [get]/usuario/roles
 - ### /anime
 -  [CRUD]
 -  /anime/{123}/registrarComentario 
 -  /anime/{123}/registrarPersonagem 
 -  /anime/{123}/comentarios 
 -  /anime/{123}/categorias 
 -  /anime/?page={numero} 
 - ### /personagem
 - [CRUD]
 - /personagem/{id_personagem:123}/registrarComentario 
 - /personagem/{id_comentarios:123}/comentarios 
 - /personagem/?pagina={numero}
 - ### /categoria
 - [crud]
 - /categoria/{id_categoria:123}/inserirAnime/{id_anime:123}


## Requisitos Funcionais da API:
 - O sistema deve: retornar uma listagem de anime com paginação de 8 animes, incluindo o link de next e preview, e a quantidade de páginas, um link para os personagens.  Na listagem é possível escolher por parâmetro que represente uma página escolhida.[ok]
 - O sistema permite que para cada Anime e Personagem, seja possível registrar/ver/deletar comentários. [ok]
 - O sistema deve: inserir/pegar uma ou mais categorias relacionadas ao anime. [ok]
 - Regra de Negócio: O usuário pode deletar os próprios comentários, mas não pode deletar os comentários dos outros.[ok] 
 - Regra de Negócio: O usuário com permissão de administrador ou permissão de moderador pode deletar os comentários de outros usuários desde que ele seja o “criador” do registro do anime. 
 - Regra de Negócio: O usuário cadastrado pode criar animes, e sendo o dono desse anime ele pode delegar responsabilidades para outros usuários, como o direito de poder deletar ou atualizar um anime 
   [ok], e administrar comentários.
 - Regra de Negócio: [atualizado 1]: O sistema deve evitar que um usuario <b>NÃO</b> cadastrado possa comentar[ok].
 - O sistema deve: receber uma requisição REST para poder inserir/atualizar/deletar/listar/visualizar um anime. [ok]
 - O sistema deve: receber uma  requisição REST para poder inserir/atualizar/deletar/listar/visualizar uma categoria. [ok]
 - O sistema deve: receber uma  requisição REST para poder inserir/atualizar/deletar/listar/visualizar um(a) personagem. [ok]
 - O sistema deve: receber uma  requisição REST para poder inserir/atualizar/deletar/listar/visualizar um(a) comentário. [ok] 
 - O sistema deve: receber uma  requisição REST para poder  inserir/atualizar/deletar/listar/visualizar um(a) usuário. [ok]
 - Definição de Dados: O usuário terá as seguintes informações: id para ser único, nome, senha, idade.[ok]
 - Definição de Dados: O comentário terá as seguintes informações: id do dono do comentario, id alvo do comentário para personagem, id alvo para anime, mensagem.[ok]
 - Definição de Dados: A categoria terá apenas o nome único e um id.[ok]
 - Definição de Dados: O anime terá uma referência para: o usuário dono, nome do anime,  resumo, descricao, uma lista de comentários, lista de personagens, author da obra.[ok]
 - Definição de Dados: O personagem tem: id , nome, idade, descrição de aparência,  história, dublador.[ok]

###  Definições de permissões
 - Um usuário comum pode: ver anime, personagem, categorías
 - Um usuário cadastrado pode tudo que o comum pode mais: criar um anime, criar personagens para anime, criar categorías, ele é o administrador do proprio anime, 
   e comentar.
 - Um usuário dono do anime pode tudo que um cadastrado pode mais: atualizar o anime, delegar sua permissão de dono para outros usuários, deletar comentários em seu anime, e no anime dos outros que têm permissões de dono.

### Requisitos não funcionais
 - O serviço é uma API rest portanto deve seguir as convenções básicas REST e Restful, recebendo parâmetros de controle e filtro na url, e retornando um arquivo no formato json.
 - Fica a cargo do programador escolher os padrões rest, restful mas o código deverá ter consistência. Exemplo que foi inicialmente determinado que o nome da rota de requisição endpoint é /usuário ou /personagem então não crie /usuários /personagem , ou se o método http delete não retorna nada em usuário, então também não deve retornar nada em personagem.

 - Será usado jwt para autenticação.

### Rotas
http://app-route/{versionNumber}:port/{controller}/method/PathParam?QueryParam=x

### Documento
https://docs.google.com/document/d/1TZDFx-bWBkMlElQt3W_nvpirw5wImVCt03tRZPkDZXE/edit?usp=sharing

### Bugs e aprendizados
Resolver o bug do relacionamento de resposta infinita: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

   - Use o @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) na propriedade 
   que @ManyToOne anota  caso venha o erro da serialização em uma propriedade que inicialmente 
   seria eager loader mais foi forçada a ser o lazy loading,
   mas saiba que usando o jackson ele traz todas as variaveis como se fossem eager mesmo 
   elas não sendo, enganando o programador.
   
```


    @ManyToOne(optional = true , fetch = FetchType.LAZY)
    @JoinColumn( name = "professor_id" )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Professor professor;
```