# Arquitetura de DW

DW tem duas arquiteturas principais:
1. Arquitetura do Fluxo de dados
    * Onde e como os dados são armazenados, o fluxo dos sistemas até os repositórios
      
    * Configuração dos repositórios
    * Controle, log e monitoração dos dados para garantir qualidade dos dados
    * 3 arquitetures:
        * DDS
        * NDS + DDS
        * ODS + DDS
    * Diferente de arquitetura de dados (modelagem de dados)
      
    ## Repositórios
    * Componentes importantes da arq. de fluxo de dados
    * Uma ou mais bases com dados
    * Pode se classificar em relação ao acesso aos dados:
        * User-facing: Disponível para usuários e aplicações
        * Interno: Usado internamente no fluxo (preparar, logar, limpar etc.)
        * Híbrido: User-facing e Interno
    * Classificações em relação ao formato de dados:
        * Stage - Interno - Transformar e preparar dados dos sistemas fontes
        * NDS - Normalized Data Store - Interno - Uma ou mais bases normalizadas para integrar dados de várias fontes em Stage
        * ODS - Operational Data Store - Híbrido - Uma ou mais bases relacionais - Dados de transações, pode usar para aplicações operacionais, versão mais recente dos dados principais
        * DDS - Dimensional Data Store - User-facing - Dados arranjados em formato dimensional para suportar consultar analíticas
    > Base de dados relacional - Com relacionamentos  
      
    > Base de dados normalizada - Pouca ou nenhuma redundância de dados  
      
    > Base de dados desnormalizada - Com alguma redundância  
      
    > Base de dados dimensional - Tabelas fatos e dimensões  
      
    > MDB - Multidimensional - Base com várias dimensões de um mesmo dado
      
    > ETL - Extract, Transform, Load - Sistema que lê dados de um repositório, transforma, e carrega em outro repositório  
      
    > Data Quality - Atividades e mecanismos para garantir que os dados estão corretos e completos - Alertas, avisos para reportar problemas e ou inconsistências - DW ajuda muito a base de produção porque pega os erros antes  
      
    > Data Firewall - Firewall com regras e inteligência para garantir o Data Quality   
      
    > Control + Audit - Sistema que controla e audita o ETL
      
    > Metadata - Contém descrição de estruturas, dados e processos dentro de um DW
      
      
    **Arquitetura de fluxo de dados é uma das primeiras coisas decididas na construção de DW**  
      
    Determina componentes a serem construídos  
    Planejado para cumprir requisitos de dados das aplicações  
    A partir daqui se define o ETL para obter os repositórios desejados  
      
    ### Arq. Completa
    ![Arquitetura de fluxo de dados completa](./img/Arquitetura_completa.png)

    ### Arq 1: DDS Simples
    * Um DDS com Stage
    * Um só repositório dimensional, com um ou mais data marts dimensionais
    > Data Mart é um grupo de tabelas fatos relacionadas e suas tabelas dimensões correspondentes
      
    ![Exemplo de arquitetura DDS Simples](./img/Arq_1_ddl_simples.png)
    
2. Arquitetura do Sistema
    * Configuração física dos componentes

