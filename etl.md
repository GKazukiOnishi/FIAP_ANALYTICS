# ETL - Projeto Físico

ETL = **Extract, Transform and Load**
* Processo de recuperar e transformar dados dos sistemas fontes
* Podemos usar a ideia de ETL tanto para receber dados quanto para enviar.

## Cálculos de tamanho, hardware
Olhar slides

## Princípios
* **Volume de dados enormes**, centezas de mega ou até dezenas de gigas
* Um sistema OLTP é feito para retornar dados em pequenos pedaços, não em grandes partes. Mas como que fazem isso sem travar o sistema transacional?
    * Horários para isso?
    * Realtime?
    * Depende muito do caso, a noite é melhor? de dia é melhor (boleto roda a noite, então de dia?)
* Deseja-se uma extração **rápida**, como 5 min, e não 3 horas. Precisa ser **pequena** (10 Mb por dia e não Gigas). E é melhor se for **menos frequente**, 1 vez ao dia e não a cada 5 min.
* Queremos não alterar o sistema fonte (às vezes não tem jeito, as vezes precisamos lockar ou algo assim)
* Sempre tomar cuidado para não atrapalhar outros sistemas (quebrar a produção, travar, deixar lento, qlqr coisa). Nem que vc precise replicar.
* Precisa entrar no DW o mais rápido possível
* Transformações costumam ser aplicadas para enquadrar nos formatos do NDS ou DDS
* Conceitos importantes
    * **Leakage**: Quando o processo acha que pegou tudo, mas perdeu algumas linhas de dados
    * **Recoverability**: O sistema precisa ser robusto o suficiente para aguentar problemas, permitir recuperar sem perda de dados

## Abordagens e Arquitetura
* Tradicional: Puxar do sistema fonte, colocar em Stage, Transformação e carregar nos repositórios NDS ou DDS
* Sem Stage: Uma alterativa seria trocar os dados em "stage" e realizar a transformação na memória. Nisso temos o sistema fonte, a transformação e o repositório apenas. (Viável quando não é uma quantidade enorme de dados, mas lembrar que caso o sistema falhe, perde tudo, precisa reprocessar)
* ELT: Extract, Load and Transform: Puxamos os dados, colocamos no DW já e só depois aplicamos as transformações e atualizando direto no NDS ou DDS. Pode ser usado quando o servidor de DB é robusto, geralmente com processamento paralelo, e não temos um servidor de ETL para o processamento (ainda pode existir, mas não faz tudo).

### Classificações
* Baseada em Consulta de Dados no sistema fonte
* Triggers na base de dados das fontes que gravam informações no DW
* Processo agendado dentro do sistema fonte exporta dados regularmente
* Log Reader lê os arquivos de log para identificar mudanças de dados e grava no DW

### Onde
* Executar ETL em um servidor separado que fica entre a fonte e as bases DW (costuma ser melhor, para não atrapalhar as fontes nem o DW)
* Executar no servidor de DW
* Executar nos servidores do sistema fonte

## Ferramentas
* Lambda Function - Pode ser usada, mas pode ficar caro dada a quantidade de dados (container costuma ficar mais barato)
    * Limitado em 15 min de execução
    * Limitada à alocação de memória de 10.240 MB
* Glue - Serviço Serverless para ETL
* DMS - DataBase Migration Service - Migrar dados do sistema fonte, minimizando inatividade no lado transacional

## Extração de dados
Primeiro estabelecemos a conexão com a fonte de dados e então começamos
<br>

Métodos para extrair:
* Tabela inteira
    * Quando a tabela é pequena (1000 linhas por aí, as vezes é melhor do que ficar buscando por algo específico)
    * Podemos optar por essa opção quando não sabemos o que é novo ou foi atualizado (mas cuidado)
* Incremental
    * Tabelas grandes, com centenas de milhões de linhas
    * Pode-se levar dias para extrair tudo, nisso a incremental pega apenas as linhas alteradas e não a tabela completa
    * Podemos nos basear em colunas timestamp, colunas sequenciais, datas de transação, triggers ou uma combinação deles
    * O código pode ir rodando a cada X tempo e verificando alteração de dado, pegando um "corte" dos dados alterados
    * LSET (Last Successfull Extraction Time) e vai salvando para saber o final do corte. CET é o atual (Currente Extraction Time), que é passado para o ETL.
        * (created >= LSET and created < CET) or (last_updated >= LSET and last_updated < CET)
        * Limitamos a query com o CET para evitar que pegue as linhas criadas após o início do processamento
    * **Tolerante a falhas**, porque você sabe dos cortes que deram certo. Qualquer coisa basta re-rodar que tudo bem
    * Mesmo que não tenha colunas de timestamp, podemos usar datas de transação, tomando cuidado para não perder dados usando datas antigas. Outra opção são campos sequenciais, nisso vamos indo conforme a numeração da sequencia (CEI e LCEI)
* Intervalo fixo
* Abordagem Push