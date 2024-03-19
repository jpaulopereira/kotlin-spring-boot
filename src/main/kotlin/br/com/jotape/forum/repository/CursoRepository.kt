package br.com.jotape.forum.repository

import br.com.jotape.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
}