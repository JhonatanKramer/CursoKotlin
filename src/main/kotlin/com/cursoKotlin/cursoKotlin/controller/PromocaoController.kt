package com.cursoKotlin.cursoKotlin.controller

import com.cursoKotlin.cursoKotlin.exception.PromocaoNotFoundException
import com.cursoKotlin.cursoKotlin.model.ErrorMessage
import com.cursoKotlin.cursoKotlin.model.Promocao
import com.cursoKotlin.cursoKotlin.model.RespostasJSON
import com.cursoKotlin.cursoKotlin.service.PromocaoService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {
    //@Autowired injeta dependencia igual o java
    // lateinit retarda a inicializacao da variavel ou seja inicia quando necessario,
    // caso fosse uam var normal já teria que ser inicializado
//    @Autowired
//    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @Autowired
    lateinit var promocaoService: PromocaoService

    // :String declara o retorno da função, caso não tenha o kotlin entende que é void
    @GetMapping("/Hello")
    fun sayhello(): String {
        return "hello word"
    }

    // retorno void
//    @GetMapping()
//    fun get(): Promocao {
//        // Criando variavel java x Kotlin
//        // Promocao p = new promocao()
//        // p.setId(1)
//        // p.setDescricao("blabla")
//        // No kotlin podemos descrever as variaveis ou preencher na ordem
//        // val promocao = Promocao(id = 1,descricao = "descricao promção")
//        val promocao = Promocao(1, "descricao promocao", "local", true, 7, 4200.00)
//        return promocao
//    }

    /// -- Jeito 1 de fazer ------
    //funciona no mesmo formato do java os endpoint
//    @GetMapping("/{id}")
//    // Executando busca por indice do vetor colocado no application
//    // funiona igual java com @PathVariable
//    fun getGetById(@PathVariable id: Long): ResponseEntity<Promocao?> {
//        var promocao = this.promocaoService.getById(id) ?:
//        throw PromocaoNotFoundException("Promoção ${id} não Localizado ")
//
//        //var status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK
//        return ResponseEntity(promocao, HttpStatus.OK)
//    }

    // -- Jeito 2 de fazer ----
    @GetMapping("/{id}")
    // Executando busca por indice do vetor colocado no application
    // funiona igual java com @PathVariable
    // Any significa que retorna qualquer coisa
    fun getGetById(@PathVariable id: Long): ResponseEntity<Any> {
        var promocao = this.promocaoService.getById(id)
        return if(promocao!= null)
        //var status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocao, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promocao Não Localizada", "Promoção ${id} não Localizado "), HttpStatus.NOT_FOUND)
    }



   // Desta forma retorna apenas o status, sem objeto e sem mensagem
//    @PostMapping()
//    // Unit significa que devolve um response sem objeto(Vazio)
//    fun create(@RequestBody promocao: Promocao): ResponseEntity<Unit> {
//        this.promocaoService.create(promocao)
//        return ResponseEntity(Unit, HttpStatus.CREATED)
//    }

    // desta forma cria um JSON com a mensagem ("message":"Cadastrado com sucesso")
//    @PostMapping()
//    // Unit significa que devolve um response sem objeto(Vazio)
//    fun create(@RequestBody promocao: Promocao): ResponseEntity<Map<String, String>> {
//        this.promocaoService.create(promocao)
//        val map = mapOf("message" to "Cadastrado com Sucesso")
//        return ResponseEntity(map, HttpStatus.CREATED)
//    }

    // desta forma cria um JSON com a mensagem e data ("message":"Cadastrado com sucesso", Date xx/xx/xxxx)
    @PostMapping()
    // Unit significa que devolve um response sem objeto(Vazio)
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostasJSON> {
        this.promocaoService.create(promocao)
        val repostaJSON = RespostasJSON("Cadastrado com sucesso", Date())
        return ResponseEntity(repostaJSON, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        this.promocaoService.delete(id)
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        this.promocaoService.update(id, promocao)
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
//    @GetMapping()
//    fun getAll(@RequestParam (required = false, defaultValue = "")localFilter:String) =
//        // Busca todos os objetos do array que contem a string passada
//        promocoes.filter { it.value.local.contains(localFilter, true)
//        }.map (Map.Entry<Long, Promocao>::value).toList() // pra cada intem de retorno devolve o valor como lista
//
//        // Isso é um map, chave e objeto(Valor) it é o imperador
//        // {1,promocao()},
//        //{2,promocao()},
//        //{2,promocao()}

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Promocao>> {
        var status = HttpStatus.OK
        var listaPromocoes =  this.promocaoService.searchByLocal(localFilter)
        if (listaPromocoes.size == 0){
            status = HttpStatus.NOT_FOUND
        }
        return ResponseEntity(listaPromocoes, status)

    }



}