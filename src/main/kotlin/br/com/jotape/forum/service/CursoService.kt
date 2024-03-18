package br.com.jotape.forum.service

import br.com.jotape.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso>) {
    //init é executada assim que uma instância da classe é criada. É usado para inicializar valores
    //ou executar lógica de inicialização antes de qualquer outro código
    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )
        cursos = listOf(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter { curso ->
            curso.id == id
        }.findFirst().get()
    }
}
