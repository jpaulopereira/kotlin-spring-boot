package br.com.jotape.forum.controller

import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.model.Topico
import br.com.jotape.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
//(private val service: TopicoService) -> instância a classe service
class TopicoController(private val service: TopicoService) { //Declara no construtor e o Spring injeta automaticamente essa classe

    @GetMapping
    fun listar(): List<Topico> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorID(@PathVariable id: Long): Topico {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody dto: NovoTopicoDTO) {
        return service.cadastrar(dto)
    }
}
