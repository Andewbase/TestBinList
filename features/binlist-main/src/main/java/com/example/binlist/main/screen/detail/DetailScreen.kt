package com.example.binlist.main.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.rememberNavController
import com.example.binlist.main.R
import com.example.binlist.navigation.MainRouter
import com.example.binlist.uikit.BinListTheme
import com.example.core.Const.TEXT_CITY_TEST
import com.example.core.Const.TEXT_PHONE2_TEST
import com.example.core.Const.TEXT_PHONE_TEST
import com.example.core.Const.TEXT_URL_TEST

@Composable
fun DetailScreen(
    detailState: DetailState,
    mainRouter: MainRouter.Base
){

    val context = LocalContext.current

    val dimen5dp = dimensionResource(id = R.dimen.margin_5)
    val dimen10dp = dimensionResource(id = R.dimen.margin_10)

   Dialog(
       onDismissRequest = {},
       properties = DialogProperties(
           usePlatformDefaultWidth = false
       )
   ) {
       Card(
           border = BorderStroke(
               width = dimensionResource(id = R.dimen.margin_1),
               color = colorResource(id = R.color.ivory)
           ),
           colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.pantone)),
           shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_15)),
           modifier = Modifier
               .fillMaxWidth(0.8f)
               .padding(top = dimensionResource(id = R.dimen.margin_12))
       ){

           Column(modifier = Modifier
               .padding(dimensionResource(id = R.dimen.margin_8))
               .fillMaxWidth()){

               Column(
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {

                   Row {
                       Text(
                           text = stringResource(id = R.string.detailsCardInfo),
                           modifier = Modifier.padding(top = dimen10dp)
                       )
                       Icon(
                           Icons.Filled.Clear,
                           contentDescription = "Clear Dialog",
                           modifier = Modifier
                               .padding(start = dimensionResource(id = R.dimen.margin_100))
                               .size(
                                   height = dimensionResource(id = R.dimen.margin_40),
                                   width = dimensionResource(id = R.dimen.margin_40)
                               )
                               .clickable {
                                   mainRouter.popBackStack()
                               }
                       )
                   }

                   Card(
                       colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.orange))
                   ) {

                       Column(
                           modifier = Modifier.fillMaxWidth(0.8f)
                       ) {
                           Row(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.Center
                           ) {
                               Text(
                                   text = detailState.cardDetailUI.number,
                                   fontSize = 25.sp
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(end = dimen10dp),
                               horizontalArrangement = Arrangement.End
                           ) {
                               Text(
                                   text = detailState.cardDetailUI.scheme,
                                   fontSize = 20.sp
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(start = dimen10dp),
                               horizontalArrangement = Arrangement.Start
                           ){
                               Text(
                                   text = stringResource(id = R.string.type)
                               )
                               Text(
                                   text = detailState.cardDetailUI.type,
                                   modifier = Modifier.padding(start = dimen5dp)
                               )
                           }
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(start = dimen10dp),
                               horizontalArrangement = Arrangement.Start
                           ){
                               Text(
                                   text = stringResource(id = R.string.brand)
                               )
                               Text(
                                   text = detailState.cardDetailUI.brand,
                                   modifier = Modifier.padding(start = dimen5dp)
                               )
                           }
                       }

                   }

                   RowDetailExample(
                       id = R.string.country_header,
                       text = detailState.cardDetailUI.countryName,
                       modifier = Modifier
                           .clickable(
                               enabled = detailState.countryNameEnabled
                           ) {
                                   Intent(
                                       Intent.ACTION_VIEW,
                                       Uri.parse(
                                           "geo:${detailState.cardDetailUI.countryLatitude},${detailState.cardDetailUI.countryLongitude}?z=5"
                                       )
                                   ).also {
                                       context.startActivity(it)
                                   }
                       }
                           .testTag(TEXT_CITY_TEST),
                       color = R.color.cyan
                   )

                    RowDetailExample(
                        id = R.string.currency_header,
                        text = detailState.cardDetailUI.currency
                    )

                   RowDetailExample(
                       id = R.string.nameBank,
                       text = detailState.cardDetailUI.nameBank
                   )



                   RowDetailExample(
                       id = R.string.bank_city_header,
                       text = detailState.cardDetailUI.cityBank
                   )

                   RowDetailExample(
                       id = R.string.url,
                       text = detailState.cardDetailUI.urlBank,
                       modifier = Modifier.clickable(
                           enabled = detailState.urlBankEnabled
                       ) {
                           Intent(
                               Intent.ACTION_VIEW,
                               Uri.parse(
                                   detailState.cardDetailUI.urlBank
                               )
                           ).also {
                               context.startActivity(it)
                           }
                       }
                           .testTag(TEXT_URL_TEST),
                       color = R.color.cyan
                   )

                   RowDetailExample(
                       id = R.string.phone_bank,
                       text = detailState.cardDetailUI.phoneBank1,
                       modifier = Modifier.clickable(
                           enabled = detailState.phoneBankEnabled
                       ) {
                           Intent(
                               Intent.ACTION_DIAL,
                               Uri.parse(
                                   "tel:${detailState.cardDetailUI.phoneBank1}"
                               )
                           ).also {
                               context.startActivity(it)
                           }
                       }
                           .testTag(TEXT_PHONE_TEST),
                       color = R.color.cyan
                   )

                   RowDetailExample(
                       id = R.string.phone_bank2,
                       text = detailState.cardDetailUI.phoneBank2,
                       modifier = Modifier.clickable(
                           enabled = detailState.phoneBankTwoEnabled
                       ) {
                           Intent(
                               Intent.ACTION_DIAL,
                               Uri.parse(
                                   "tel:${detailState.cardDetailUI.phoneBank2}"
                               )
                           ).also {
                               context.startActivity(it)
                           }
                       }
                           .testTag(TEXT_PHONE2_TEST),
                       color = R.color.cyan
                   )
               }

           }

       }
   }

}

@Composable
fun RowDetailExample(
    @StringRes id: Int,
    text: String,
    modifier: Modifier = Modifier,
    @ColorRes color: Int =  R.color.ivory,
    dimen: Dp = dimensionResource(id = R.dimen.margin_5)
){

    Row(
        modifier = Modifier
            .padding(top = dimen)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = id)
        )
        Text(
            text = text,
            color = colorResource(id = color),
            modifier = modifier.padding(start = dimen)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {

    BinListTheme {
        DetailScreen(
            detailState = DetailState(),
            mainRouter = MainRouter.Base(rememberNavController())
       )
    }
}