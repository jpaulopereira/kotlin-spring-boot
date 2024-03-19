package br.com.jotape.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico (

    //val nomeDoAtributo: Tipagem (?)==não obrigatório = valor inicial = null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne //Um tópico pertence a um único curso
    val curso: Curso,

    @ManyToOne
    val autor: Usuario,

    @Enumerated(value = EnumType.STRING)  //salva o nome da constante. Não a ordem Ex: 1,2..
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @OneToMany(mappedBy = "topicos")
    val respostas: List<Resposta> = ArrayList()
)