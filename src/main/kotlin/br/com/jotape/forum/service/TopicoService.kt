package br.com.jotape.forum.service

import br.com.jotape.forum.dto.AtualizacaoTopicoDTO
import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.dto.TopicoDTO
import br.com.jotape.forum.exception.NotFoundException
import br.com.jotape.forum.mapper.NovoTopicoMapper
import br.com.jotape.forum.mapper.TopicoViewMapper
import br.com.jotape.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoMapper: NovoTopicoMapper,
    private val notFoundMessage: String = "Topico não Encontrado"
) {
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoDTO> {
        val topicos = if (nomeCurso == null) {
            topicoRepository.findAll(paginacao)
        } else {
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos
            .map { topico ->
                topicoViewMapper.map(topico)
            }
    }

    fun buscarPorId(id: Long): TopicoDTO {
        val topico = topicoRepository
            .findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDTO): TopicoDTO {
        val topico = novoTopicoMapper.map(dto)
        topicoRepository.save(topico)

        //plus é usada para concatenar coleções, como listas, conjuntos ou arrays
        //topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoDTO): TopicoDTO {
        val topico = topicoRepository.findById(dto.id)
            .orElseThrow { (NotFoundException(notFoundMessage)) }

        topico.titulo = dto.titulo
        topico.mensagem = dto.titulo

        //minus(remove da lista) plus(adiciona um topico novo atualizado)
        //topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        topicoRepository.deleteById(id)
    }
}