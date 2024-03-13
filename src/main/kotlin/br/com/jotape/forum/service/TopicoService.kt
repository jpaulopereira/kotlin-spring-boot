package br.com.jotape.forum.service

import br.com.jotape.forum.model.Curso
import br.com.jotape.forum.model.Topico
import br.com.jotape.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService {

    fun listar(): List<Topico> {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Silva",
                email = "silva@email.com"
            )
        )
        return Arrays.asList(topico, topico, topico)
    }
}