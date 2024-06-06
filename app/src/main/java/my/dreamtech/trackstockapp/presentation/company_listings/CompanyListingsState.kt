package my.dreamtech.trackstockapp.presentation.company_listings

import my.dreamtech.trackstockapp.domain.model.CompanyListingModel

data class CompanyListingsState(
    val companies: List<CompanyListingModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
