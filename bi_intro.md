# BI

## Processo decisório
Analisar o passado (informações históricas, experiências acumuladas)
Bolar planos para executar e atingir objetivos
**Tomada de Decisão**
Gerar simulações

Dados = 80%
Informação = Dado + Contexto = 15%
Inteligência = Conhecimento = Informação + Análise = 5% (Tomada de decisão)

### Sistema Transacional x Suporte à decisão

1. Transacional
- Operacional
- CRUD
- Online
- App
2. Decisão
- Informações estratégicas
- Consultas complexas com grandes volumes de dados
- Em geral é alimentado do transacional, mas pode ser de outros
- Poucos updates, raro

## DW
- Armazém de dados
- Voltado à tomada de decisão
- Derivada de bancos de dados operacionais
- Pode ser usado como base para OLPA e extração/análise de conhecimento/informação

- Satisfazer necessidades de análise de informação
- Monitorar e comparar atual com o passado
- Estimar futuro

> Dados orientados a assuntos, integrada, voltada à tomada de decisão
> Extrai, limpa, ajusta e carrega dados
> Temporal, séries temporais, não volátil (Não muda, fica registrado)

> Data Warehousing: Processo de construção e suporte de DW
> BI: Tela que o gerente olha para tomada de decisão

### Características

1. Orientado a assuntos
- Vendas de produto
- Vendas da loja
- Atendimento
- Diagnósticos de pacientes
- Rendimento de estudantes

2. Integrado
- Diferentes fontes/tipos de dados organizados em uma visão unificada e consistente
- Tem várias informações que podem ser entendidas no conjunto e separados

> Esqueça Forma Normal (FN), em BI a ideia é armazenar informações temporais

3. Séries temporais
- Armazena por um tempo superior ao usual comparado com os bancos transacionais


