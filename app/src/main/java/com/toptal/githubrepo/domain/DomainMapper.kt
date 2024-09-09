package com.toptal.githubrepo.domain

/**
 * Each mapper that maps network to domain (or cache to domain) model and vice versa should implement this interface
 * Network is one part of the system, domain is another and they should not be mixed same goes for cache
 * @property DomainModel is a data model from domain layer (used for UI and UseCases)
 * @property T is a data model that comes from network or cache layers
 */

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}