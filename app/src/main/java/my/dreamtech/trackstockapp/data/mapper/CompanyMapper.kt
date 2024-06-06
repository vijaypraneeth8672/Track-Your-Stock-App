package my.dreamtech.trackstockapp.data.mapper

import my.dreamtech.trackstockapp.data.local.CompanyListingEntity
import my.dreamtech.trackstockapp.data.remote.dto.CompanyInfoDto
import my.dreamtech.trackstockapp.domain.model.CompanyInfo
import my.dreamtech.trackstockapp.domain.model.CompanyListingModel

fun CompanyListingEntity.toCompanyListing(): CompanyListingModel{
    return CompanyListingModel(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListingModel.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}