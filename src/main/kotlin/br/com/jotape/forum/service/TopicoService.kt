package br.com.jotape.forum.service

import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.dto.TopicoDTO
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
    private val novoTopicoMapper: NovoTopicoMapper
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

    fun cadastrar(dto: NovoTopicoDTO) {
        val topico = novoTopicoMapper.map(dto)
        val novoId = topicos.size.toLong() + 1
        topico.id = novoId
        //plus é usada para concatenar coleções, como listas, conjuntos ou arrays
        topicos = topicos.plus(topico)
    }
}