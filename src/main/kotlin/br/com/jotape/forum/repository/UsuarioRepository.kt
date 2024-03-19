package br.com.jotape.forum.repository

import br.com.jotape.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
}