package br.com.jotape.forum.dto

import br.com.jotape.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoDTO(

    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
