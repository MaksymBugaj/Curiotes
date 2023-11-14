package com.bussar.curiosity.domain.usecase.base

abstract class SuspendUseCase<in Param, Result> {
    abstract suspend fun execute(params: Param): Result
}