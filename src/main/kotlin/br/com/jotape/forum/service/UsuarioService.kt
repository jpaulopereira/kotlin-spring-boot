package br.com.jotape.forum.service

import br.com.jotape.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService (private var usuarios: List<Usuario>) {

    init {
        val autor = Usuario(
            id = 1,
            nome = "Kotlin",
            email = "usuario@gmail.com"
        )
        usuarios = listOf(autor)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter { usuario ->
            usuario.id == id
        }.findFirst().get()
    }
}





