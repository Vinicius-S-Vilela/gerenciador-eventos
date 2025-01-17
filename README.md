# Sistema Gerenciador de Eventos

## Introdução
O sistema gerenciador de eventos foi desenvolvido para auxiliar a Universidade Municipal de São Caetano do Sul (USCS) na organização de eventos internos, como mostras de extensão, ciclos de palestras, encontros de iniciação científica, semanas de computação, entre outros. Este sistema abrange funcionalidades como cadastro de eventos, gestão de atividades e participantes, geração de certificados e emissão de listas de presença.

## Estrutura do Sistema

### Modelo Inicial do Sistema

Abaixo está o diagrama UML que foi elaborado como ponto de partida para o desenvolvimento do sistema:

![Image](https://github.com/user-attachments/assets/09f1a13c-24a5-4b47-86a3-4845c837a510)

Este diagrama reflete a concepção inicial das classes, métodos e interações do sistema. No entanto, durante o processo de codificação, algumas mudanças foram implementadas para melhor atender aos requisitos do projeto. Entre essas mudanças, destacam-se:

1. Adaptações de métodos e atributos:

  - Foram adicionados métodos específicos, como `cancelarInscricao` e `realizarInscricaoAtividade`, que não estavam inicialmente no modelo.

2. Evolução da estrutura:

  - A associação entre `Palestrante` e `Atividade` foi ajustada para garantir maior clareza e funcionalidade no vínculo entre essas classes.

  - A classe `Local` foi ajustada para refletir melhor os campi disponíveis e a gestão de espaços.

3. Alterações para melhorias futuras:

  - O uso de listas estáticas foi mantido no modelo inicial, mas há planos para migrar para uma base de dados relacional para aumentar a escalabilidade e persistência de dados.

## Estrutura do Sistema

### Classes Principais
O sistema é composto pelas seguintes classes principais:

1. Classe `Pessoa` (Abstrata)

   Representa os atores do sistema (Participantes, Palestrantes e Organizadores) com os atributos e métodos:
- Atributos:

  - `nome(String)`: Nome completo.

  - `email(String)`: E-mail de contato.

  - `telefone(String)`: Telefone de contato.

  - `endereco(String)`: Endereço completo.

- Métodos:

  - `alterarDados(String nome, String email, String telefone, String endereco)`: Permite a atualização dos dados cadastrais.

  - Getters e setters para cada atributo.

2. Classe `Participante`

    Especializa a classe `Pessoa` para representar os participantes do evento.

- Atributos:

  - `inscricoes(List)`: Lista de atividades nas quais o participante está inscrito.

- Métodos:

  - `cadastrarParticipante(Scanner sc)`: Permite o cadastro de um novo participante.

  - `verInscricoes()`: Lista todas as atividades em que o participante está inscrito.

  - `cancelarInscricao(Scanner sc)`: Cancela a inscrição em uma atividade.

  - `exibirDados()`: Exibe os dados do participante.

3. Classe `Palestrante`

    Especializa a classe `Pessoa` para representar palestrantes.

- Atributos:

  - `especialidade(String)`: Área de conhecimento do palestrante.

  - `listaPalestrantes(static ArrayList)`: Lista de palestrantes cadastrados.

- Métodos:

  - `cadastrarPalestrante(Scanner sc)`: Permite o cadastro de um novo palestrante.

  - `atribuirPalestra()`: Vincula o palestrante a uma atividade.

  - `exibirDados()`: Exibe os dados do palestrante.

4. Classe `Organizador`

    Especializa a classe `Pessoa` para representar os organizadores dos eventos.

- Métodos:

  - `cadastrarOrganizador(Scanner sc)`: Permite o cadastro de um organizador.

  - `gerenciarEvento()`: Garante a administração de eventos.

  - `exibirDados()`: Exibe os dados do organizador.

5. Classe `Evento`

    Representa um evento gerenciado pelo sistema.

- Atributos:

  - `nome(String)`: Nome do evento.

  - `dataInicio(Date)` e `dataFim(Date)`: Período do evento.

  - `organizador(Organizador)`: Responsável pelo evento.

  - `local(String)`: Local do evento.

  - `atividades(List)`: Lista de atividades incluídas no evento.

  - `listaEventos(static ArrayList)`: Lista global de eventos cadastrados.

- Métodos:

  - `cadastrarEvento(Scanner sc, Organizador organizador)`: Permite o cadastro de um novo evento.

  - `listarEventos()`: Exibe todos os eventos e suas respectivas atividades.

  - `alterarLocalEvento(Scanner sc)`: Permite alterar o local de realização do evento.

  - `cadastrarAtividade(Scanner sc)`: Adiciona uma nova atividade ao evento.

  - `removerAtividade(Scanner sc)`: Remove uma atividade do evento.

  - `inscreverParticipanteAtividade(Scanner sc, Participante participante)`: Inscreve um participante em uma atividade do evento.

6. Classe `Atividade`

    Representa uma atividade dentro de um evento.

- Atributos:

  - `nomeAtividade(String)`: Nome da atividade.

  - `tipoAtividade(String)`: Tipo de atividade (palestra, minicurso, etc.).

  - `data(Date)` e `horario(LocalTime)`: Período de realização.

  - `espaco(String)`: Local físico onde a atividade ocorrerá.

  - `vagas(int)`: Número de vagas disponíveis.

  - `palestrante(Palestrante)`: Responsável pela atividade.

  - `participantes(List)`: Lista de participantes inscritos.

- Métodos:

  - `realizarInscricaoAtividade(Participante participante)`: Inscreve um participante na atividade.

7. Classe `MenuRepository`

    Controla os menus e interações do usuário no sistema.

- Métodos:

  - `menuParticipante()`: Menu de operações para participantes.

  - `menuPalestrante()`: Menu de operações para palestrantes.

  - `menuOrganizador()`: Menu de operações para organizadores.

8. Classe `Local`

    Representa os locais onde as atividades serão realizadas.

- Atributos:

  - `campi(List)`: Lista de campi disponíveis para realização dos eventos.

- Métodos:

  - `Local()`: Inicializa a lista de campi.

  - `getCampi()`: Retorna a lista de campi.

9. Classe `Certificado`

    Permite a geração de certificados de participação para os participantes.

- Métodos:

  - `emitirCertificado(Scanner sc)`: Permite selecionar um participante e uma atividade para a geração do certificado.

## Funcionalidades Principais

- Cadastro e gestão:

  - Cadastrar/remover/alterar atividades, participantes, palestrantes e eventos.

  - Alterar o local-sede de um evento.

- Participação:

  - Realizar ou remover inscrições em atividades.

  - Emitir certificados de participação.

- Consulta:

  - Listar atividades em um local específico.

  - Visualizar todas as atividades de um evento ou inscrições de um participante.

## Manual de Instalação

1. Requisitos:

    - Ambiente Java instalado (JDK 11 ou superior).

    - IDE compatível, como IntelliJ IDEA ou Eclipse.

2. Execução:

    - Compile o código com `javac`.

    - Execute a classe principal `Main`.

3. Entrada:

    - Utilize os menus para navegar entre as opções de cadastro, gerenciamento e consulta.

4. Testes:

    - Utilize os objetos pré-criados para simular operações.

## Possíveis Melhorias

Embora o sistema atenda aos requisitos iniciais, algumas melhorias poderiam ser implementadas para aumentar sua eficiência e escalabilidade:

1. Integração com Banco de Dados:

    - Substituir o uso de listas estáticas por um banco de dados relacional (como MySQL ou PostgreSQL) para armazenamento persistente de dados, garantindo maior segurança e escalabilidade.

2. Interface Gráfica:

    - Desenvolver uma interface gráfica amigável utilizando JavaFX ou frameworks web para melhorar a experiência do usuário.

3. Controle de Conflitos:

    - Implementar verificações para evitar conflitos de horário entre atividades no mesmo local.

4. Notificações:

    - Adicionar um sistema de notificações por e-mail para lembrar os participantes sobre eventos ou atividades.

5. Relatórios:

    - Gerar relatórios detalhados sobre eventos, inscrições e atividades para os organizadores.

6. Suporte a Multiusuários:

    - Incluir autenticação e autorização para diferentes perfis de usuários (administradores, participantes e palestrantes).

## Materiais Suplementares

Para complementar a compreensão do projeto, incluí o material fornecido pelo professor, que serviu como referência inicial para a concepção do sistema. Esse documento contém orientações e requisitos que nortearam as primeiras etapas do desenvolvimento.

[Projeto.pdf](https://github.com/user-attachments/files/18458172/Projeto.pdf)


