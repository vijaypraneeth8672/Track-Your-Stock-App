package my.dreamtech.trackstockapp.presentation.track_stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import my.dreamtech.trackstockapp.R
import my.dreamtech.trackstockapp.data.track.TrackEntity
import my.dreamtech.trackstockapp.ui.theme.DarkBlue


@Composable
@Destination
fun AddEditStockDetailScreen(
    id: Long,
    viewModel: TrackViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id!=0L){
        val stock = viewModel.getAStockById(id).collectAsState(initial = TrackEntity(0L,"","","","",""))
        viewModel.stockCompanyNameState = stock.value.companyName
        viewModel.stockInvestedState = stock.value.invested
        viewModel.stockProfitState = stock.value.profit
        viewModel.stockLossState = stock.value.loss
        viewModel.stockDateState = stock.value.date
    }else{
        viewModel.stockCompanyNameState = ""
        viewModel.stockInvestedState = ""
        viewModel.stockProfitState = ""
        viewModel.stockLossState = ""
        viewModel.stockDateState = ""
    }

    Scaffold (
        topBar = {
                 TopAppBar (title =
                 { Text(text =if(id != 0L) "Update Stock Details"
                 else "Add Stock Details")})
        },
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(DarkBlue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            StockTextField(label = "Company Name",
                value = viewModel.stockCompanyNameState,
                onValueChange = {newString ->
                    viewModel.onStockCompanyNameChanged(newString)
                })

            Spacer(modifier = Modifier.height(2.dp))

            StockTextField(label = "Invested Amount",
                value = viewModel.stockInvestedState,
                onValueChange = {newString ->
                    viewModel.onStockInvestedChanged(newString)
                })

            Spacer(modifier = Modifier.height(2.dp))

            StockTextField(label = "Invested On",
                value = viewModel.stockDateState,
                onValueChange = {newString ->
                    viewModel.onStockDateChanged(newString)
                })

            Spacer(modifier = Modifier.height(2.dp))

            StockTextField(label = "Profit (in $) ",
                value = viewModel.stockProfitState,
                onValueChange = {newString ->
                    viewModel.onStockProfitChanged(newString)
                })

            Spacer(modifier = Modifier.height(2.dp))

            StockTextField(label = "Loss (in $)",
                value = viewModel.stockLossState,
                onValueChange = {newString ->
                    viewModel.onStockLossChanged(newString)
                })

            Spacer(modifier = Modifier.height(10.dp))

            Button(modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(20.dp),onClick ={
                if(viewModel.stockCompanyNameState.isNotEmpty() &&
                    viewModel.stockInvestedState.isNotEmpty() &&
                    viewModel.stockDateState.isNotEmpty() &&
                    viewModel.stockProfitState.isNotEmpty() &&
                    viewModel.stockLossState.isNotEmpty()){
                    if(id!=0L){
                        viewModel.updateStock(
                            TrackEntity(
                                id = id,
                                companyName = viewModel.stockCompanyNameState.trim(),
                                invested = viewModel.stockInvestedState.trim(),
                                profit = viewModel.stockProfitState.trim(),
                                loss = viewModel.stockLossState.trim(),
                                date = viewModel.stockDateState.trim()
                            )
                        )
                    }else{
                        viewModel.addStock(
                            TrackEntity(
                                companyName = viewModel.stockCompanyNameState.trim(),
                                invested = viewModel.stockInvestedState.trim(),
                                profit = viewModel.stockProfitState.trim(),
                                loss = viewModel.stockLossState.trim(),
                                date = viewModel.stockDateState.trim()
                            )
                        )
                    }
                }
                scope.launch {
                    navigator.navigateUp()
                }
            }) {
                Text(text = if (id != 0L) "Update" else "Add",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}


@Composable
fun StockTextField(
    label : String,
    value : String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(value = value, onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = colorResource(id = R.color.white),
            unfocusedBorderColor = colorResource(id = R.color.white),
            cursorColor = colorResource(id = R.color.white),
            focusedLabelColor = colorResource(id = R.color.white),
            unfocusedLabelColor = colorResource(id = R.color.white),
        )

    )
}