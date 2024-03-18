package br.com.jotape.forum.service

import br.com.jotape.forum.dto.NovoTopicoDTO
import br.com.jotape.forum.model.Topico
import org.springframework.stereotype.Service
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val curosService: CursoService,
    private val usuarioService: UsuarioService
) { // = ArrayList() inicializa a lista com um array vazio

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
    }

    fun cadastrar(dto: NovoTopicoDTO) {
        //plus é usada para concatenar coleções, como listas, conjuntos ou arrays
       topicos = topicos.plus(
            Topico(
                id = topicos.size.toLong() + 1,
                titulo = dto.titulo,
                mensagem = dto.mensagem,
                curso = curosService.buscarPorId(dto.idCurso),
                autor = usuarioService.buscarPorId(dto.idAutor)
            )
        )
    }
}