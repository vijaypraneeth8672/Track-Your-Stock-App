package my.dreamtech.trackstockapp.generative_AI

sealed interface GenerativeAiState{
    object Initial : GenerativeAiState
    object Loading : GenerativeAiState
    data class Success(
        val outputText: String
    ) : GenerativeAiState

    data class Error(val error: String) : GenerativeAiState

}