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
}

// Ref: posts.user_id > users.id // many-to-one

// Ref: users.id < follows.following_user_id

// Ref: users.id < follows.followed_user_id
