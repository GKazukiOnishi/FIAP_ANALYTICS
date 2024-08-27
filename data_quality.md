# Data Quality
Se os dados não fizerem sentido, vale a pena usar DW?  
Quanto custa um dado não confiável? E a carreira dos envolvidos?  

* Dados Incorretos (Correção)
* Dados Faltando (Completeza)
* Dados não sincronizados com outro (Consistência)
* Dados estimados (Precisão)
* Dados desatualizados (Atualização temporal)

<br>

Por isso a qualidade dos dados deve ser pensada o mais cedo possível, junto do início do projeto.  
  
**DQ visa prevenir dados ruins** e consertar se necessário. 
  
Por isso precisamos definir regras de qualidade para saber o que está ruim, filtrar eles, reportar, monitorar, limpar eles...  

## Exemplo
Detectar dados que indiquem um cancelamento de assinatura antes da data da adesão dela.  

## Processo de DQ
* Data Firewall - Programa que verifica dados que entram
* Metadata - Base de dados com as regras de qualidade de dados
* Base de dados DQ - Dados ruins detectados pelo firewall
* Reports & Notification - Leem a base DQ e informa a situacao de qualidade de dados atual
* Correction - Processo de correcao dos sistemas fontes

### Detalhes
* Informações de auditorias costumam ser gravados junto
    * Sistema de origem
    * Tabela de origem
    * Data e hora
    * Regra que detectou o dado ruim
    * Onde o dado seria carregado
* A correcao pode ser manual apos um aviso da base DQ aos responsaveis, ou automáticas (não costuma ser uma boa ideia, pois tende a manter os dados incorretos)
* O firewall pode ser posicionado antes da Stage, ODS etc., antes da Stage costuma ser uma boa ideia, apesar de deixar a extração lenta, garante que nenhum dado ruim chega no DW, nem mesmo na Stage. Outra opção é verificar esses dados na própria fonte antes da extração usando WHERE etc. (mas aí não temos como registrar o dado ruim)
* Em geral costumam estar em uma coleção de registros, não sozinho.
* O firewall pode ser colocado após a carga de dados (antes da DDS) para permitir regras envolvendo cálculos esperados (exemplo: Total de transacoes, vendas esperadas comuns etc.)
* As vezes os DWs salvam os dados mesmo estando incorretos, mas lembre-se que se causar probelmas de analises precisarao ser removidos.
* Dados a serem salvos na base DQ
    * Accuracy
    * Precision
    * Timeliness
    * Completeness
    * Consistency

## Data Cleaning e Matching

### Data Cleansing
* Identificar e corrigir dados "sujos"

### Matching
* Tentativa de identificar dados correspondentes a outras, ou seja, casos de duplicações. (Muito comum em dados de tipo char)
    * Los Angles / Los Angeles
    * Robert Peterson / Bob Peterson
    * 5,029 e 5,03 - Se arredondamos com 2 casas é igual
* Tipos conhecidos
    * Exata: Tudo igual
    * Fuzzy: Dados similares obedecendo uma pontuação
    * Baseada em regras: Quando films é sempre igual a movies

## Cross-Checking
Quando verificamos fontes externas para garantir que os dados são corretos ou que fazem sentido (CPF na receita federal etc.). Além disso os dados externos podem vir em diferentes formatos.  
Abordagens:
* Trazer os dados para o DW: Tipo os dados dos correior para verificar CEP
* Não trazer e buscar sempre que precisar, usando webservices por exemplo

## Regras de DQ
