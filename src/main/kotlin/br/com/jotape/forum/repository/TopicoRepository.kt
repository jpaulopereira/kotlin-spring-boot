package br.com.jotape.forum.repository

import br.com.jotape.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long> {
}