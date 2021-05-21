package com.cursoKotlin.cursoKotlin

import com.cursoKotlin.cursoKotlin.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CursoKotlinApplication{

	// metodo statico da class
	companion object{
		val initialPromocoes = arrayOf(
			Promocao(1, "descricao promocao", "local", true, 7, 4200.00),
			Promocao(1, "Ilhas Maldivas", "Maldivas", true, 7, 4999.99),
			Promocao(1, "Arroio do meio", "casa", true, 7, 1.00),
			Promocao(1, "namorados", "gramado", true, 7, 4200.00)
		)
	}

}



fun main(args: Array<String>) {
	runApplication<CursoKotlinApplication>(*args)
}
