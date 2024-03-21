package br.com.jotape.forum.repository

import br.com.jotape.forum.dto.TopicoPorCategoriaDTO
import br.com.jotape.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {
    //A JPA sabe que precisa fazer um select from topico, então,
    // perceberá que Curso é um relacionamento, entrará nesse relacionamento e verá que precisa filtrar pelo nome,
    // fazer os joins e executar a consulta exatamente como esperado.
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>


    //JPQL fornece, para cada categoria de curso, o número de tópicos abertos.
    @Query("SELECT new br.com.jotape.forum.dto.TopicoPorCategoriaDTO(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDTO>
}