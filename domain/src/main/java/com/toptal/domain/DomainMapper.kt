package com.toptal.domain

/**
 * Each mapper that maps network to domain (or cache to domain) model and vice versa should implement this interface
 * @property DomainModel is a data model from domain layer (used for UI and UseCases)
 * @property T is a data model that comes from network or cache layers
 */

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}