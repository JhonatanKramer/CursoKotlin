package com.cursoKotlin.cursoKotlin.service

import com.cursoKotlin.cursoKotlin.model.Promocao

interface PromocaoService {


    // Representa o optional da classe java
    // Optiona<Promocao> = :Promocao?
    fun getById(id:Long) :Promocao?
    fun delete(id:Long)
    fun update(id:Long, promocao:Promocao)
    fun searchByLocal(local:String): List<Promocao>
    fun create(promocao: Promocao)
}