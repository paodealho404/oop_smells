# Programação Orientada a Objetos 💻- Projeto de Software/ - 2020 - PLE
## Este repositório é destinado ao Projeto Final da disciplina de Projeto de Software ministrada no Instituto de Computação - UFAL

## Nesta implementação, foram aplicados 3 Design Patterns
## Strategy Pattern disponível em: [Strategy](https://github.com/paodealho404/oop_smells/tree/main/model/strategypattern) 📊 
## Command Pattern disponível em: [Command](https://github.com/paodealho404/oop_smells/tree/main/model/commandpattern) 📊 
## Quanto ao terceiro design pattern (Move Accumulation to Collecting Parameter) não foi necessário criar novas classes, apenas implementar diversos métodos auxiliares novos em praticamente todas as classes


### A implementação do Strategy Pattern surgiu da necessidade de gerar os relatórios para as classes de Laboratório de Pesquisa, Projeto, Aluno, Professor e Pesquisador, uma vez que ambos compartilhavam o método gerarRelatorio(), porém, cada classe gerava seu próprio relatório, logo, classes diferentes com mesmo comportamento implementados de maneira diferente caracterizavam uma boa abertura para aplicação do Strategy Pattern. Cada classe ganhou portanto uma instância de relatório e os métodos para gerarRelatorio() dependiam apenas da instância do relatório que lhes foi dada.

### A implementação do Command Pattern surgiu da necessidade de tornar genérico o método de mudarStatus(), executado pelo administrador. Anteriormente o Administrador ficava responsável por conhecer a lógica de negócio da mudança do status de um Projeto, para cada caso de mudança, o que não é o ideal. A solução foi implementar uma interface Command geral para o Administrador, a qual seria carregada com as classes ProjetoAndamento e ProjetoConcluido, desse modo, os projetos conhecem a lógica de mudança para Concluído ou Andamento e o Administrador (Invoker), so precisa carregar uma classe de comando (Command) e solicitar através deste ao Projeto (Receiver) que altere seu estado para Concluído ou Andamento.

### A implementação do Move Accumulation to Collecting Parameter consistiu em reconhecer todos os métodos que executavam uma implementação muito inconsistente e com alto índice de acomplamento e modularizar ainda mais estes métodos, desse modo, a quantidade de métodos aumentou substancialmente, porém o código ficou muito mais limpo e manutenível
