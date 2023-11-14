package com.bussar.curiosity.domain.usecase.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in Param, Result> {
    abstract fun execute(params: Param): Flow<Result>
}