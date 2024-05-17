# Snowflaking
Quando surgem tabelas de dimensões desnormalizadas, ou seja, com repetições de valores textuais, pode ser que projetistas queiram "normalizar" elas.  
  
Por exemplo, imagine uma tabela com 150.000 produtos que são de 50 departamentos distintos. Uma opção seria simplesmente armazenar redundantemente a descrição do departamento junto dos outros dados dos produtos, aceitando que a informação repetiria, a outra envolveria normalizar e criar uma tabela de Departamento e associar cada produto a um código de departamento.  
  
Essa normalização das tabelas dimensões é o **Snowflaking**: Atributos redundantes são removidos da dimensão desnormalizada e colocados em tabelas dimensões secundárias.  
  
Porém, quando paramos para pensar nas premissas de projeto:
* Facilidade de Uso
* Desempenho  
  
O snowflaking se torna um complicador, tornando tudo mais complexo e dificultando para os otimizadores das bases de dados, levando a lentidões.  
Lembre-se que, apesar de economizar um pouco de espaço, esse espaço é mínimo comparado ao tamanho da tabela fato. Se tratando de comparar um pequeno ganho em volume de dados em comparação a Desempenho na consulta e Facilidade de Uso.

# Muitas Dimensões
Uma tabela fato tende a ser normalizada e compacta naturalmente, já que apenas se conecta com dimensões não correlacionadas.  
  
Algo comum de acontecer são desnormalizações da tabela fato, em que por terem muitos dados úteis e usados nas dimensões, essas informações são trazidas direto para a linha da fato e surgem novas dimensões menores.  
Nesse caso a fato acaba se relacionando com diversos dados de dimensão e são chamadas de **centopéias**.  
  
**As tabelas fato, ao contrário da dimensão, ganham muito ao conseguirem economizar espaço.**  
A maior parte das fatos costumam terem menos de 15 dimensões, sendo elas suficientes para representar a maioria dos processos de negócio. Passando disso é interessante tentar combinar dimensões correlacionadas.  
  
Nesse cenário é interessante comparar se a quantidade de chaves estrangeiras de dimensões na fato compensam a diversidade de dimensões que surgiriam, se isso é realmente necessário.  
Se ter mais chaves estrangeiras na fato compensa para ter aquelas novas dimensões e poder analisar aqueles novos dados direto na fato.

# SCD = Esecidi
### Slowly Changing Dimension
Uma técnica usada para armazenar os valores históricos de atributos de uma dimensão.  
Temos duas opções quando algum dado de dimensão muda, podemos sobrescrever os antigos ou preservar os antigos.  
  
Para presentar, temos dois métodos:
1. Armazená-los como linhas
    Nesse caso apenas inserimos um novo registro com um indicador de que é o mais atual, ou ativo (ou pegar o registro de último ID).  
    Com isso se torna possível comparar ganhos após a alteração, por exemplo.  
    Vai ocupar mais espaço? É tãoo pouco comparado à fato, que nem faz diferença.  
2. Armazená-los como colunas
    Nesse caso adicionamos uma coluna para representar a nova informação, fazendo o mesmo para cada nova atualização.  
    Nisso acabam surgindo várias colunas que complicam na montagem de select e quando surge a necessidade de uma nova atualização.  
  
Dessa forma temos:
1. SCD tipo 1 - Sobrescreve os valores antigos
2. SCD tipo 2 - Adiciona uma nova linha com a nova informação
3. SCD tipo 3 - Mantém os valores antigos e adiciona uma nova coluna
  
Para escolher um ou outro devemos pesar o armazenamento de dados por linha ou coluna, com a flexibilidade de construção de consultas para acesso ao dado.  
Na prática quase nem tem dilema, não tem muitos motivos para não adicionar uma nova linha só.  
  
# RCD
### Rapidly Changing Dimensions