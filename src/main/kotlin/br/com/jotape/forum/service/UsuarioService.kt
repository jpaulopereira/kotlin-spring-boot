package br.com.jotape.forum.service

import br.com.jotape.forum.model.Usuario
import br.com.jotape.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService (private val usuarioRepository: UsuarioRepository){

    fun buscarPorId(id: Long): Usuario {
       return usuarioRepository.findById(id).orElse(null)
    }
}





