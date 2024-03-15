# Modelo Dimensional - Parte 1

> DW: Orientado a assunto, Integrado, Série Temporal e Não Volátil

> Arquitetura do Sistema: On Premise + Cloud Computing

* Os olhos das empresas são os dados, sem isso você não enxerga e é ultrapassado. Empresas baseadas em dados tendem a se manter firmes no mercado
    > Um dos principais ativos da organização é a informação  
      
    Sistemas Transacionais -> Os dados entram  
    Data Warehouse -> Os dados são lidos  
* Modelagem:
    * Sistemas Transacionais -> 3FN
    * DDS do data warehouse -> Modelagem dimensional
* Independente da técnica, a maioria deles seguem os mesmos conceitos

    ## Histórico
    * Desde a década de 60 já usavam termos como fatos e dimensões
    * Não foi uma pessoa específica que inventou, (não foi o Ralph Kimball)
    
> Fato - Algo que aconteceu, ocorrências, situações, algo do mundo real. DW é orientado a assunto porque trabalha com isso, porque queremos saber mais sobre o mundo real usando fatos. Entender o seu negócio através dos fatos, ele sozinho não diz muito.  
  
**Fato**: Dados de medição numérica de desempenho

## Algumas métricas de negócio
* CAC - Custo de Aquisição do Cliente: Valor investido em marketing e vendas divido pela qtd de clientes conquistados
* LTV - Valor do Ciclo de Vida do cliente: Valor médio produzido por um cliente durante seu período ativo na empresa
* ROI - Retorno sobre o investimento: Medir quanto dinheiro a empresa ganha ou perde para cada investimento
* Churn - Taxa de Churn - Medir perda de clientes por um período

## Tabela Fato
* Fundamental para o modelo dimensional
* Medição numérica de desempenho do negócio
    * Ex: Produtos sendo vendidos, qtd vendida e total de vendas  
            Data (FK)  
            Produto (FK)  
            Loja (FK)  
            Qtd  
            Venda em Dolar  
    * Medição - Intersecção de todas as dimensões (Data, produto e loja)
    * Lista de dimensões define o **grão** da tabela fato, o escopo da medição
        * Pensa no total de produtos * total de lojas * estimativa de tempo em sla, 10 anos - Isso vai te dar uma noção da quantidade de registros a serem armazenados
        > As dimensões podem ser reutilizadas em outras tabelas fatos
* Evento do negócio
* Cada linha da tabela é uma medição
* Todas as medições devem estar no mesmo grão
* Medições são definidas na **fase de levantamento de requisitos** - Precisa conversar com o cliente, entender o que quer
* Podemos ter **fatos calculados** na tabela fato também - Facilita para evitar que cada um faça de um jeito, duplique, esqueça etc. - O importante é ter um padrão, fazer em um lugar só, na tabela ou na tela
    * Média, valor total etc.
## Os fatos
* Os mais úteis são numéricos e aditivos
* Aditividade
    * Crual, porque não analisamos uma linha, analisamos um conjunto de linhas, o trimestre, o ano etc.
    * Usamos centenas, milhares, milhões de linhas de uma vez
    * Não aditivos - Não faz sentido somar
    * Semi-aditivios - Só faz sentido somar em algumas dimensões - Mais difíceis de tratar
    * É legal marcar na documentação isso, dizer se é aditivo ou não
    ### Modelagem de fatos
    * O mais importante sempre vai ser entender as pessoas de negócios, o que querem
    * Dados de verdade + Requisitos de Negócio = Modelo Dimensional, Grão, Dimensão, Fatos, Processo de negócio
    * Sair mergulhando nos dados e procurando correlações tendem a levar ao engano, correlações existem em todo canto