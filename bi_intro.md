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

![Diferença entre BD e DW de uma tabela preços](./img/img_bd_x_dw.png)

4. Não volátil
- Não são modificados que nem nos sistemas transacionais
- São carregados apenas e acessados para leitura
- Só alterados para correções, mas no geral não tem, quando maduro o sistema dificilmente acontece

## Transacional x Dimensional
1. Objetivo: Atividades cotidianas x Análise de negócio
2. Uso: Operacional x Informativo
3. Processamento: OLTP x OLAP
4. Unidade de trabalho: CRUD x Carga e consulta
5. Usuários: Operadores (muitos) x Gerência (poucos)
6. Interação dos usuários: Ações pré-definidas x Pré-definida e ad-hoc (customização)
7. Dados: Operacionais x Analíticos
8. Volume: Pode ser alto (MB - GB) x Muito alto (GB - TB)
9. Histórico: 60 a 90 dias x Vários anos
10. Granularidade: Detalhada (baixa) x Detalhada e consolidada (alta)
11. Redundância: Não ocorre (só p/eficiência) x Pode ocorrer
12. Estrutura: Estática x Variável
13. Manutenção: Mínima é o desejável x Constante
14. Atualização: Contínua (tempo real) x Periódica (snapshots - retratos)
15. Integridade: Transação x Cada atualização
16. Acesso a registros: Poucos - por transação x Muitos - para consolidação
17. Índices: Poucos/simples x Muitos/complexos
18. Função dos índices: Localizar um registro x Agilizar consultas