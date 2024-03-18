package br.com.jotape.forum.service

import br.com.jotape.forum.dto.AtualizacaoTopicoDTO
import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.dto.TopicoDTO
import br.com.jotape.forum.exception.NotFoundException
import br.com.jotape.forum.mapper.NovoTopicoMapper
import br.com.jotape.forum.mapper.TopicoViewMapper
import br.com.jotape.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoMapper: NovoTopicoMapper,
    private val notFoundMessage: String = "Topico não Encontrado"
) { // = ArrayList() inicializa a lista com um array vazio

    fun listar(): List<TopicoDTO> {
        return topicos.stream().map { topico ->
            topicoViewMapper.map(topico)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoDTO {
        val topico = topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDTO): TopicoDTO {
        val topico = novoTopicoMapper.map(dto)
        val novoId = topicos.size.toLong() + 1
        topico.id = novoId
        //plus é usada para concatenar coleções, como listas, conjuntos ou arrays
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoDTO): TopicoDTO {
        val topico = topicos.stream().filter { topico ->
            topico.id == dto.id
        }.findFirst().orElseThrow{(NotFoundException(notFoundMessage))}

        val topicoAtualizado = Topico(
            id = dto.id,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        //minus(remove da lista) plus(adiciona um topico novo atualizado)
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().orElseThrow{(NotFoundException(notFoundMessage))}
        topicos = topicos.minus(topico)
    }
}