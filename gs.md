// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs

Table DATA {
  id int [primary key]
  data date
  dia int
  mes int
  ano int
  diasemana varchar2(45) 
}

Table LOCALIZACAO {
  id int [primary key]
  cidade varchar2(45)
  estado varchar2(45)
  pais varchar2(45)
  regiao varchar2(8)
  cluster varchar2(45)
}

Table RESPONSAVEL {
  id int [primary key]
  nome varchar2(80)
}

Table TIPO_LIXO {
  id int [primary key]
  categoria varchar2(45)
  reciclavel bool
}

Table FATO {
  id_data int [primary key]
  id_localizacao int [primary key]
  id_responsavel int [primary key]
  id_tipo_lixo int [primary key]
  quantidade int
}

Ref: "DATA"."id" < "FATO"."id_data"

Ref: "LOCALIZACAO"."id" < "FATO"."id_localizacao"

Ref: "RESPONSAVEL"."id" < "FATO"."id_responsavel"

Ref: "TIPO_LIXO"."id" < "FATO"."id_tipo_lixo"