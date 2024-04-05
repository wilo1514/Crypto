package com.example.crypto.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.crypto.model.Asset
import com.example.crypto.viewmodel.AssetsViewModel

@Composable
fun AssetList(viewModel: AssetsViewModel) {
    val asset = viewModel.assets
    LaunchedEffect(Unit) {
        viewModel.fetchAssets()
    }
    LazyColumn (modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        items(asset){currentAsset ->
            AssetRow(asset = currentAsset)
            Divider()
        }

    }
    /*Column(
        modifier= Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){

        AssetRow(
            Asset(
                id = "Bitcoin",
                name = "Bitcoin",
                symbol = "BTC",
                price = 6000.0,
                percentage = 10.0
            )
        )
        Divider()
        AssetRow(
            Asset(
                id = "Ethereum",
                name = "Ethereum",
                symbol = "ETH",
                price = 6000.0,
                percentage = -2.0
            )
        )
    }*/
}
@Composable
fun AssetRow(asset: Asset){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
        Box(
            modifier=Modifier
                .padding(horizontal = 8.dp)
        ){
            if(LocalInspectionMode.current){
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Icono de agregar",
                    modifier = Modifier
                        .size(30.dp)
                )
            }else {

                AsyncImage(
                    model = "https://assets.coincap.io/assets/icons/${asset.symbol.lowercase()}@2x.png",
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }


        Column{
            Text(text= asset.name,
                fontSize=14.sp,
                color=Color.Black
            )
            Text(text= asset.symbol,
                fontSize=14.sp,
                color=Color.Gray)
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text="$${asset.price}",
            color=Color.Black,
            modifier=Modifier
                .padding(horizontal=16.dp)
        )
        Text(
            text="${asset.percentage}%",
            fontSize = 14.sp,
            color=if(asset.percentage>=0) Color.Green else Color.Red,
            modifier=Modifier
                .padding(horizontal=14.dp)
        )
    }
}

@Preview(
    showBackground=true,
    showSystemUi = true,
)

@Composable
fun AssetRowPreview(){
    AssetList(AssetsViewModel())

}