package br.com.jotape.forum.controller

import br.com.jotape.forum.dto.AtualizacaoTopicoDTO
import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.dto.TopicoDTO
import br.com.jotape.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
//(private val service: TopicoService) -> instância a classe service
class TopicoController(private val service: TopicoService) { //Declara no construtor e o Spring injeta automaticamente essa classe

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
       @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoDTO> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorID(@PathVariable id: Long): TopicoDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid dto: NovoTopicoDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoDTO> {
        val topicoDTO = service.cadastrar(dto)
        val uri = uriBuilder.path("/topicos/${topicoDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoDTO)
    }

    @Transactional
    @PutMapping
    fun atualizar(@RequestBody @Valid dto: AtualizacaoTopicoDTO): ResponseEntity<TopicoDTO> {
        val topicoDTO = service.atualizar(dto)
        return ResponseEntity.ok(topicoDTO)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}
