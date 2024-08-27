# Cálculos de tamanho, hardware - Projeto Físico
Um dos maiores desafios é atender à performance e segurança desejada na implementação física do projeto, que costumam compor grande parte dos requisitos não funcionais.
* Como deixar disponível 24/7?
* Sem downtime maior que 1 hora por mês
* Reprocessamentos possiveis
* Tempo de acesso aos relatorios interior a 10 segundos

### Importante
* É preciso pensar em failover, em clusters para redundância
* NLB - Network Load Balanced é uma opção para tratar os servidores idênticos
* SAN - Storage Area Network com fibra óptica redundante pode ajudar
* Memória, Processador de cada servidor precisa ser dimensionado e estudados

<br>

Mas como prever a complexidade necessária? Como calcular o tamanho necessário? Como prever o ETL/ELT, Stage, Regras, considerar índices, partições etc., quantidade de bases no mesmo servidor, considerar crescimento futuro? Como?

<br>

Isso é a complexidade dessa tarefa

## Storage
O calculo de storage pode ser feito considerando o tamanho de cada linha por tabela, assim como já fizemos, mas dessa vez podemos considerar margens de crescimento, incremento esperado, total calculado etc.
<br>
Depende do servidor e da utilidade dele. É para Stage? É para o DW em si?
<br>

### Cálculo
(Tamanho da Linha) * 150% * (Incremento Diário) * (Total de dias previstos)

<br>

* É interessante considerar um total de 30% reservado para metadados e indices também!
* Partições de arquivos de dados
* RAID 5 para desempenho
* Arquivo de Log em arquivo separado

## Configurando Databases

* Nomes curtos e claros
* Collation (como serão ordenados os caracteres)
* Case Sensitivity
* Criação de filegroups
* Tamanhos iniciais e de incremento bem calculados e previstos
* Máximo grau de paralelismo possível para usar todos os processadores
* Desligar funcionalidades não utilizadas

(Imagens de exemplo nos slides)

### Particionamento
Dois tipos:
* Vertical: Divide uma tabela em várias menores, cada uma contendo algumas **colunas** da tabela original
* Horizontal: Divide uma tabela em várias menores, cada uma contendo algumas **linhas** da tabela original

<br>
Em geral:

* Usamos **Horizontal Partitioning**
* Conteúdo é esperado para ser **cronológico**
* Ao organizar fisicamente as tabelas por data, costuma levar a melhores performances, pois a maioria das consultas envolveriam a mesma partição.

## Banco de dados em Cloud
* Big Query - Google - Consultas de alto desempenho usando SQL
* RedShift - AWS para alto desempenho usando SQL

<br>

Cada um tem os seus preços e políticas. Isso possibilita e facilita o cálculo dados os requisitos de cada projeto! (Exercícios no slide)

<br>

Com isso agora somos capazdes de esboçar arquitetura de hardware para DWs quaisquer!

## Índices
Podem melhorar significativamente o desemenho de um DW.

### Nas dimensões
A Surrogate Key (SK) é um exemplo, que inclusive costuma ser um clusterizado, permitindo definir a ordem física de armazenamento.
<br>
Podem ser usados também filtros comuns das outras colunas.

### Nas fatos
Definimos a PK da tabela conforme o negócio, permitindo indexar os dados pela PK (composta mesmo).  
* Se indexarmos só por uma das colunas da PK, costuma ser 10x mais lento.
* Não compensa indexar tabelas pequenas (porque costuma dar mais problemas na manutencao e não compensa o ganho de desempenho)
* 1000 linhas para baixo não vale a pena
* Vale a pena estudar planos de execuções das queries

### Extras
* Podemos indexar colunas após análises de tunning
* Podemos indexar cada uma das SK da tabela Fato, conhecida como bitmapping (dessa vez sem clusterizar)

# ETL - Projeto Físico

ETL = **Extract, Transform and Load**
* Processo de recuperar e transformar dados dos sistemas fontes
* Podemos usar a ideia de ETL tanto para receber dados quanto para enviar.

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
    * Extraimos um certo número de registros ou um período deles
    * Por exemplo, os ultimos 6 meses, ou ultimas 100.000 linhas da tabela
* Abordagem Push

## Tabelas Relacionadas
É importante lembrar que caso uma linha seja extraida e tenha relacionamento com outras, essas precisarao ser carregadas tambem.  
Geralmente buscamos a primeira tabela e todos os dados relacionados de cada registro extraido.

## Outras fontes
As fontes nem sempre sao tabelas, podem ser:
* XML
* Flat Files (delimitados ou posicionadas)
* Excel
* Web Logs
* Web Services
* Filas dem ensagens
* E-mails