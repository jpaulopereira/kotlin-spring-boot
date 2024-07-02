package br.com.jotape.forum.service

import br.com.jotape.forum.model.Curso
import br.com.jotape.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val cursoRepository: CursoRepository) {
    /*
    init é executada assim que uma instância da classe é criada.
    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )
        cursos = listOf(curso)
    }
    */

    fun buscarPorId(id: Long): Curso {
        return cursoRepository.findById(id).orElse(null)
    }
}
