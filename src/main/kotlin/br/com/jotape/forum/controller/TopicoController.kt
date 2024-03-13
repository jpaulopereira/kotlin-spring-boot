package br.com.jotape.forum.controller

import br.com.jotape.forum.model.Curso
import br.com.jotape.forum.model.Topico
import br.com.jotape.forum.model.Usuario
import br.com.jotape.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topicos")
//(private val service: TopicoService) -> instância a classe service
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<Topico> {
        return service.listar()
    }
}
