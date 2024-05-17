# Granularidade
Medida de acordo com o tamanho do grão, em que quanto menor, maior o nosso detalhe.  
  
* MAIOR GRANULARIDADE (Grão grosso) = MENOR DETALHAMENTO
* MENOR GRANULARIDADE (Grão fino) = MAIOR DETALHAMENTO  
  
Geralmente os dashboards abrem em maior granularidade, para exibir coisas mais resumidas e menos detalhadas, para em caso de necessidade ir aumentando.  
  
Pensando em custos:  
* Particionamento em grandes unidades = Muitos dados = Alto custo de processamento, com Baixo custo de comunicação (trafega poucos dados)
* Particionamento em pequenas unidades = Poucos dados = Baixo custo de processamento, Alto custo de comunicação (mais dados para trafegar) - Isso pode ser resolvido ao carregar os dados só quando clicam no detalhe  
  
## Exemplos
1. Fui ao shopping ontem (grão enorme) (**Roll Up** - Grão Grosso)
2. Estive ontem shopping Tamboré das 19:45 as 21:32 no evento "Alunos da FIAP", onde fiz um mini curso de minecraft. (grão pequeno com muitos dados) (**Drill Drown** - Grão Fino)  
  
## Granularidade (Requisitos)
Maior grão = Produto e Data  
Menor grão = Produto, Data, Loja, Vendedor, Comprador e Promoção  
  
Quanto Menor o grão - Maior detalhamento, flexibilidade, volume, velocidade (menos coisa para sumarizar)
Quanto Maior o grão - Menor detalhamento, flexibilidade, volume, velocidade (mais coisa para sumarizar)  
  
