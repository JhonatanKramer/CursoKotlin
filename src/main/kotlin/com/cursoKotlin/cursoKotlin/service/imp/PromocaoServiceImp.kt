package com.cursoKotlin.cursoKotlin.service.imp

import com.cursoKotlin.cursoKotlin.model.Promocao
import com.cursoKotlin.cursoKotlin.service.PromocaoService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
@Service
class PromocaoServiceImp : PromocaoService {

    // metodo statico da class para simular um banco de dados
    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "descricao promocao", "local", true, 7, 4200.00),
            Promocao(2, "Ilhas Maldivas", "Maldivas", true, 7, 4999.99),
            Promocao(3, "Arroio do meio", "casa", true, 7, 1.00),
            Promocao(4, "namorados", "gramado", true, 7, 4200.00)
        )
    }

    // implementa as busca com controle de concorrencia
    // ficando array(id,Promocao)
    // Quando uma função só tem uma linha não precisa de corpo
    // @Bean deixa a funçao disponivel par atodas as classes sem precisar dar new, parecido com metodo static java

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))


    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        //        // Busca todos os objetos do array que contem a string passada
        promocoes.filter {
            it.value.local.contains(local, true)
        }.map(Map.Entry<Long, Promocao>::value).toList()// pra cada intem de retorno devolve o valor como lista
    // Isso é um map, chave e objeto(Valor) it é o imperador
//        // {1,promocao()},
//        //{2,promocao()},
//        //{2,promocao()}


    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }


}