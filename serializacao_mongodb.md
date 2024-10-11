# Serialização de Dados

## XML
Muito usado antigamente e hoje também, mas causa muito overhead.
* Um dado simples de um caractere acaba precisando de muito mais bytes para ser representado: <abre>Dado</abre>
* É fácil de adicionar novos atributos e de entender, porém precisa de um Parser para interpretar o dado no código
    * Quando tem listas precisa de até percorrer recursivamente os itens da lista

## JSON
O mais comum hoje em dia, pois tem um Overhead menor que o XML, não tem isso de abrir e fechar Tag.
* Fácil de entender
* Comunicação nativa com JavaScript
* Reduziu 40% de overhead em comparação com XML
* Por conta disso a comunicação com a tela facilitou muito, além do REST/HTTP.
* O Parse de gigabytes em JSON é muito muito mais rápido que XML

# NoSQL Orientado a Documento
MongoDB, por exemplo.
* Armazena atributos e valores
* Pode ser muilti-valorado
* Sem estrutura
* Permitem dados semi estruturados (arquivos, por exemplo)
* MongoDB, CouchDB, RavenDB

## MongoDB
* Código aberto, alto desempenho, escrito em C++
* Permite as mesmas operações de um relacional, insert update delete select index etc.
* Armazena documentos BSON, semelhantemente ao JSON
* Baixa latência, Alta disponibilidade de conjuntos Expressivos de dados
    * Nem sempre consistente, não é certeza que vai retornar o dado correto logo após o insert

### Como funciona
* Databases
* Collections
* Documents
    * Estrutura associada às classes do sistema (chave valor)
* Definição de Fields em runtime
* Bem flexível
* Permite ACID a partir da versão 4.0
* Mongoose - Object Models
* ID automatizado que nasce com o registro - ObjectId (12 bytes hexadecimal)
    * 4 timestamp
    * 3 machine id
    * 2 process id
    * 3 bytes incrementer
* Índices
    * Crescentes ou Decrescentes

Comandos nos slides  

https://www.ibm.com/products/informix