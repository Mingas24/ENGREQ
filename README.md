# Produto Viável Mínimo

## Backlog - Definição de Prioridades
A prioridade da implementação dos requisitos para o sistema é definida numa escala de 1 a 3, sendo 1 a prioridade mais alta, 2 a prioridade média e 3 a prioridade mais baixa.
### Como apicultor quero realizar a declaração anual de existências

- realizarDeclaracaoAnual - cria a declaração anual de existências de acordo com a informação do utilizador - **Prioridade 1**
- getApicultorInfo - recolhe a informação do apicultor através do seu NIF - **Prioridade 2**
- getApiariosInfo - recolhe a informação dos apiários do apicultor - **Prioridade 2**
- getColmeiasInfo - recolhe a informação das colmeia de um apiário - **Prioridade 2**

### Como apicultor quero efetuar pedido de transumância
- criarPedidoTrans - cria o pedido de transumância, com a nova localização desejada -  **Prioridade 1**
- alterarZona - alterar a zona do apiário, realizando assim a transumância -  **Prioridade 2**

### Como apicultor quero poder criar um apiário, efetuar o respectivo pedido de instalação e registar a sua constituição/informação.

* criarApiario - cria o apiario e respetivas colmeias e respetivas alças e regista no sistema - **Prioridade 1**
* createPedidoInstalacao - cria o pedido instalação - **Prioridade 3**
* respostaPedidoInstalacao - regista resposta ao pedido de instalação - **Prioridade 3**

### Como apicultor quero realizar a inspeção do apiário e registar a mesma

* create - cria novo registo de inspeção de apiário - **Prioridade 1**
* getApiarioById - recolhe a informação do apiário ao qual foi feita a inspeção através do seu id - **Prioridade 2**
* getApiariosByApicultorId - obtem todos os apiários do apicultor através do id do apicultor - **Prioridade 2**
* update - altera registo de inspeção já inserida no sistema - **Prioridade 2**
* getInspecaoDetailsById - consulta de detalhes de inspeção inserida no sistema através do id da inspeção - **Prioridade 2**
* getInspecoesByApiarioId - obtem todas as inspeções realizadas em apiário através do id do apiário - **Prioridade 2**
* deleteInspecao - apaga registo de inspeção de apiário, no caso de haver engano no registo de inspeção - **Prioridade 3**


### Como apicultor quero realizar a Cresta

* getApiariosByApicultorId - obtem todos os apiários do apicultor através do id do apicultor - **Prioridade 2**
* GetColmeiasByApiarioId - obtem as colmeias de um determinado apiario - **Prioridade 2**
* GetAlcasByColmeiaId - obtem as alças de uma colmeia para alterar os valores - **Prioridade 1**
* realizarCresta - envia as quantidades de Mel e Polen (em Kg) das Alças ao realizar a Cresta - **Prioridade 1**

### Outros Requisitos
* Email Handler para notificações - **Prioridade 2**
* Login - **Prioridade 3**
* Boostrap Apicultores - **Prioridade 2**
* Mock do lado do sistema de backend para providenciar as funcionalidade de
  execução da DGADR, DGAV e outras. (Portal Backend) - **Prioridade 2**
