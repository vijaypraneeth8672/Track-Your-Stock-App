package my.dreamtech.trackstockapp.presentation.track_stock

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import my.dreamtech.trackstockapp.data.track.TrackEntity

@Composable
fun StockItem(stock : TrackEntity,modifier: Modifier,
              viewModel: TrackViewModel= hiltViewModel()){
    Card (
        modifier = modifier,
        elevation = 10.dp,
        backgroundColor = Color.White

    ){
            Row (
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically, // Align elements vertically
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(modifier = Modifier.weight(1f)) { // Adjust column to take most of the space
                    Text(text = "Company Name: ${stock.companyName}", fontWeight = FontWeight.SemiBold)
                    Text(text = "Invested Amount: $${stock.invested}", fontWeight = FontWeight.SemiBold)
                    Text(text = "Invested On: ${stock.date}", fontWeight = FontWeight.SemiBold)
                    Text(text = "Profit: $${stock.profit}", fontWeight = FontWeight.SemiBold)
                    Text(text = "Loss: $${stock.loss}", fontWeight = FontWeight.SemiBold)
                }

                IconButton(onClick ={
                    viewModel.deleteStock(stock)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete" )
                }
            }



    }
}