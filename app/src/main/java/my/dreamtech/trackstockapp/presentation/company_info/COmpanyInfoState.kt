package my.dreamtech.trackstockapp.presentation.company_info

import my.dreamtech.trackstockapp.domain.model.CompanyInfo
import my.dreamtech.trackstockapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)