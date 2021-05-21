package com.cursoKotlin.cursoKotlin.controller

import com.cursoKotlin.cursoKotlin.model.Promocao
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PromocaoController {
    // :String declara o retorno da função, caso não tenha o kotlin entende que é void
    @RequestMapping(value = ["/Hello"], method = arrayOf(RequestMethod.GET))
    fun sayhello(): String {
        return "hello word"
    }

    // retorno void
    @RequestMapping(value = ["/promocao"], method = arrayOf(RequestMethod.GET))
    fun getpromocao(): Promocao {
        // Criando variavel java x Kotlin
        // Promocao p = new promocao()
        // p.setId(1)
        // p.setDescricao("blabla")
        // No kotlin podemos descrever as variaveis ou preencher na ordem
        // val promocao = Promocao(id = 1,descricao = "descricao promção")
        val promocao = Promocao(1, "descricao promocao", "local", true, 7, 4200.00)
        return promocao
    }


}