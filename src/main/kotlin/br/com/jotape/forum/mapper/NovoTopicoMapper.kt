package br.com.jotape.forum.mapper

import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.dto.TopicoDTO
import br.com.jotape.forum.model.Topico
import br.com.jotape.forum.service.CursoService
import br.com.jotape.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoMapper(
    private val curosService: CursoService,
    private val usuarioService: UsuarioService
) : Mapper<NovoTopicoDTO, Topico> {
    override fun map(topico: NovoTopicoDTO): Topico {
        return Topico(
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = curosService.buscarPorId(topico.idCurso),
            autor = usuarioService.buscarPorId(topico.idAutor)
        )
    }
}
