# Surrogate Keys
Chave substituta gerada no DW. PK das tabelas.  
Se escrevermos errado alguma informação, ao invés de dar update ou apagar registro, só adicionamos uma nova SurrogateKey com a mesma chave transacional.  
Exemplo:
* Tabela Fato - FK Data, FK Produto, Qtde, Valor Total
* Dimensão Produto - Surrogate Key, Cod_Prod, Nome Produto -> É aqui em que podemos ter duas Surrogate Keys com o mesmo Cod_Prod, indicando uma atualização do produto

----  

* Essas chaves artificiais permitem isolar o ambiente das mudanças operacionais, então facilitam as operações, são mais rápidas porque costumam ser números, evita os problemas originários do transacional (rg como PK)
* Atualizar um registro não costuma ser muito fácil, porque aparecem em vários lugares e você precisa dar cascade para atualizar todos os lugares, precisa dar depara etc.
* 1 byte a mais em uma PK de uma tabela com 1 milhão é 1 MegaByte a mais, em 1 bilhão é 1 GB, e vai indo. Se tiver índice o registro vai estar lá também e vai ser maior ainda.
* Permite ter um histórico do negócio, mesmo que seja para atualizar a descrição, porque vai que a mudança da descrição causou mudanças nas vendas.
  
> Chaves Artificiais - Internos incrementados sequencialmente, sem significado de negócio fora do modelo, serve só para juntar o fato com a dimensão  

Se temos vendas sem promoção, melhor do que não registrar ou colocar NULL, é bom ter uma surrogate key que identifica quando não temos promoção, porque aí é só filtrar por ela. No mundo transacional isso não existe, mas aqui faz sentido, porque não temos como registrar na tabela fato um ID vazio, precisa de algo lá.

* Muito útil para suportar mudanças de atributos operacionais
    * Só altera lá se você quiser aceitar perder histórico, mas aí você perde informação que pode ser útil
    * BI é temporal, precisa contar história

## Tabela Fato sem fatos
Como responder quais produtos estavam em promoção mas não foram vendidos?  
* A tabela só armazena SKUs (produtos específicos) vendidos, não temos linhas para zero vendas, aquilo que não vendeu
* Se criar, começa a ficar poluído
* O que não vendeu ainda está na tabela de produtos, teve promoção também, mas não está na tabela fato
* É bom saber isso, porque aí você pode limpar o estoque, mudar estratégia, trocar etc.
* Coisa parada, recurso parado, é custo querendo ou não. É desperdício, poderia ganhar mais com isso, poderia gastar menos.
### Factless Tabela
Uma nova tabela fato para cobertura das promoções  
Chaves:
* Data
* Produto
* Loja
* Promoção
E lá a gente cadastra quando não teve venda. É bem parecida com a fato de vendas, mas o grão registra a venda independente de ter a venda ou não.  
Tem esse nome de Factless porque não registra métricas, só fala
> Analisar os prefixos de telefone permite ter uma noção da região de onde estão ligando. Se você amarra isso com quem realmente comprou, você pode perceber que a maioria das ligações acontece de regiões em que as pessoas não fecharam as compras. Então significa que precisa abrir uma unidade em outra região. Ao invés de pensar que está bom porque tem muita ligação.  
  
> O que precisamos negar para atender os requisitos? Onde não está ganhando valor? Onde está a exceção? O que você não está vendo?  
  
Resolução: Juntar a tabela de produtos em promoção com os produtos vendidos, a interseção são os produtos em promoção e vendidos, e aqueles que só tem na tabela de produtos em promoção são aqueles que não venderam. Por outro lado, quem só está na tabela de produtos vendidos e não tem na tabela de promoção, são aqueles que não tiveram promoção mas foram vendidos!  
  
Nisso podemos fazer LEFT JOIN, algoritmos, NOT EXISTIS, NOT IN etc. Dependendo do banco varia qual é mais performático.  
  
**Negação: Mostra muito mais que a afirmação, vende muito mais**  
  
Exemplos:
1. Salas de cinema sem ninguém
2. Filmes não assistidos em uma sala, mas que é visto em outras (a sala é o problema)
3. Horas em que o filme não é assistido
Isso permite trocar a estratégia e ter filmes sempre assistidos, que lucram, sem desperdício  
1. Equipamentos de academia não usados
2. Clientes que não vão na academia mas que pagam
4. Localização dos equipamentos que são menos usados por determinados sexos