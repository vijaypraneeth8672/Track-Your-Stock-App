package my.dreamtech.trackstockapp.generative_AI

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import my.dreamtech.trackstockapp.BuildConfig
import javax.inject.Inject


@HiltViewModel
class GenerativeAiViewModel @Inject constructor() : ViewModel(){

    private val _uiState : MutableStateFlow<GenerativeAiState> = MutableStateFlow(GenerativeAiState.Initial)
    val uiState = _uiState.asStateFlow()

    private lateinit var generativeModel: GenerativeModel

    init {
        val config = generationConfig {
            temperature = 0.70f
        }

        generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = BuildConfig.apiKey,
            generationConfig = config
        )
    }

    fun questioning(userInput: String,selectedImages: List<Bitmap>){
        _uiState.value = GenerativeAiState.Loading
        if(selectedImages.isEmpty()){
            val prompt = userInput
        }
        val prompt = "Take a look at images, and then answer the following question: $userInput"

        viewModelScope.launch(Dispatchers.IO){
            try {
                val content = content {
                    for(bitmap in selectedImages){
                        image(bitmap)
                    }
                    text(prompt)
                }

                var output = ""
                generativeModel.generateContentStream(content).collect{
                    output += it.text
                    _uiState.value = GenerativeAiState.Success(output)
                }
            }catch (e:Exception){
                _uiState.value =GenerativeAiState.Error(e.localizedMessage ?: "Error in Generating content")
            }
        }
    }

}