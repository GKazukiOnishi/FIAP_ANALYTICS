# Big Data e NoSQL
Big Data - Volume Enorme de Dados em um BI  
NoSQL - Capacidade para Sistemas Distribuídos  

## Big Data
In Data We Trust!  
  
Information Technology - Artificial Intelligence - Big Data - Todos juntos no Google Trends hoje  
  
Mudanças de Paradigmas em tudo.  
Locadoras? TV? Mapas?  

## NoSQL

### Relacional
* Tabelas
* PKs
* Relacionamentos
* FKs
* Registros armazenados em Tabelas
* Cardinalidade
* Normalização
* Formas Normais

### NoSQL - Not Only SQL - Algo a mais
Direcionado a problemas diferentes dos bancos relacionais

* Não tem linguagem padronizada
* Tratamento particular para Transações
* Não segue ACID
* Problemas com Integridade

### Quando usar NoSQL
* Volume de dados
* Frequência de acesso aos dados
* Concorrência de dados
* Escalabilidade
* Natureza dos dados
* Integridade e Consistência
* Tipos de Operações envolvidas

## ACID
* Atomicidade
* Consistency
* Isolation
* Durability

## CAP
* Consistência - Capacidade de se manter consistente após operações, refletindo a alteração em todos os "nós" no caso de sistemas distribuídos
* Disponibilidade (Availability) - Capacidade de continuar disponível mesmo que ocorram problemas
* Tolerância a Partição (Partition Tolerance) - Capacidade para funcionar mesmo quando algum dos nós cair ou se desconectar.

### BASE - Propriedades
Filosofia baseada em priorizar disponibilidade em detremimento da consistência
* Basicamente Disponível - Basically available - Garante a Availability do CAP, mas o dado pode vir inconsistente ou defasado.
* Eventualmente Consistente - Eventualy Consistency - O sistema eventualmente se tornará consistente, se propagando conforme o tempo
* Estado Flexível - Soft State - O sistema muda com o tempo.

### Tipos de bases NoSQL
* Chave-Valor
    * Tabelas de Hash Distribuídas
    * Indexação pela chave, só funciona a busca pela chave
* Column-Oriented
    * Diferente do relacional em que armazenamento diferentes linhas, nesse tipo de banco tempos os dados sendo armazenados todos na coluna, de forma contigua
    * Tem destaque para OLAP
* Document-Oriented
    * Documento é uma coleçao de atributos e valores
    * Pode ser multi-valorado
    * Não tem schema ou estrutura fixa
    * Permitem armazenar dados semi estruturados
* Graphs-Oriented
    * Baseados em grafos
    * Priorizam o relacionamento dos dados e a sua conexão
    * Os dados são as conexões entre nós, arestas, atributos

# Análise de Complexidade
A ideia é ter uma noção do quanto cada algoritmo escala conforme os Inputs.  
Cada estrutura de dado, por exemplo, tem uma complexidade nas suas operações CRUD.  
Assim como algoritmos de ordenação etc.