package com.bussar.curiosity.data.mapper.base

interface DataMapper<Data, Domain> {
    fun mapToData(domainModel: Domain): Data
    fun mapToDomain(data: Data): Domain
}

interface DataMapperWithParam<Data, Domain, Param> {
    fun mapToData(domainModel: Domain, param: Param): Data
    fun mapToDomain(data: Data): Domain
}