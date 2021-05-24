package com.cursoKotlin.cursoKotlin.model

import com.fasterxml.jackson.annotation.JsonInclude

// utilizando o data antes da classe ele adiciona os getter, setter, toString e etc...
// Funciona igual @Data do Java

// @JsonInclude(JsonInclude.Include.NON_NULL) todos os objetos com valor null serao retirados do JSON
// ORIGINAL --------------------RETORNO
// "nome":"jose" --------------"nome":"jose"
// "CPF":"12345678901" --------"CPF":"12345678901"
// "telefone":null ------------
// Jackson tem diversar outras funcionalidade como formatar data e hora, ignorar campos e etc ...
// Mais explicações aula 28 no udemy sobre kotlin
@JsonInclude(JsonInclude.Include.NON_NULL)
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



