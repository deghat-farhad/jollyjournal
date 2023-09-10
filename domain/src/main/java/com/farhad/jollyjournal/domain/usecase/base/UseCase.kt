package com.farhad.jollyjournal.domain.usecase.base

interface UseCase<T> {
    suspend operator fun invoke():Result<T>
}