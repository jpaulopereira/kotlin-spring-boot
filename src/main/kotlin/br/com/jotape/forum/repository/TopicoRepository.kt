package br.com.jotape.forum.repository

import br.com.jotape.forum.dto.TopicoPorCategoriaDTO
import br.com.jotape.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query("SELECT new br.com.jotape.forum.dto.TopicoPorCategoriaDTO(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDTO>
}