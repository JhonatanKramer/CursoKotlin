package com.cursoKotlin.cursoKotlin.model

// utilizando o data antes da classe ele adiciona os getter, setter, toString e etc...
// Funciona igual @Data do Java
data class Promocao(
     /* existe val e var no kotlin
     Val é a variavel final e não pode ser atribuido valor sendo apenas para leitura e imutavel
     Var é a variavel mutavel e pode ser atribuido valor
      */

    val id: Long,
    val descricao: String,
    val local: String,
    val isAllInclusive: Boolean,
    val qtdDias: Int,
    val price: Double,

)



