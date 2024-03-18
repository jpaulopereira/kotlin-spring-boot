package br.com.jotape.forum.mapper

interface Mapper<T, U> {
    fun map(topico: T): U
}