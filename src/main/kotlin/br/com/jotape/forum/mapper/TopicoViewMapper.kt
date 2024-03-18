package br.com.jotape.forum.mapper

import br.com.jotape.forum.dto.TopicoDTO
import br.com.jotape.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoDTO> {

    override fun map(topico: Topico): TopicoDTO {
        return TopicoDTO(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
    }
}