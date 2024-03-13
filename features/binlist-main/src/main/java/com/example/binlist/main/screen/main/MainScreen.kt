package com.example.binlist.main.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.binlist.main.R
import com.example.binlist.main.navigation.BankCardScreen
import com.example.binlist.uikit.BinListTheme
import com.example.binlistdata.entity.BankCardItemUI
import com.example.core.Const.CHIP_CARD
import com.example.core.Const.CLEAR_DIALOG
import com.example.core.Const.COUNTRY_EMOJI
import com.example.core.Const.EMPTY_LIST
import com.example.core.Const.NAME_BANK
import com.example.core.Const.NUMBER_CARD
import com.example.core.Const.SCHEME
import com.example.core.Const.SEARCH_CARD
import com.example.core.Const.TWO_INT

@Composable
fun MainScreen(
    mainState: MainState,
    send: (MainEvent) -> Unit,
    listCard: List<BankCardItemUI>,
    navController: NavController
){

    val dimen10dp = dimensionResource(id = R.dimen.margin_10)
    val dimen24dp = dimensionResource(id = R.dimen.margin_24)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.orange))
    ) {
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_40))
                .verticalScroll(rememberScrollState())
            ) {

                TextFieldExample(
                    value = mainState.textValue,
                    onValueChanged = { send(MainEvent.UpdateTextValue(it.take(mainState.maxChar))) },
                    onCliCk = {
                        send(MainEvent.Navigate(navController))
                        send(MainEvent.ShowNumberCard(mainState.textValue, mainState.navigate))
                    },
                    enabled = !mainState.loading
                )

            if (mainState.error != null) Snackbar(
                containerColor = colorResource(id = R.color.pantone),
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.margin_20),
                    start = dimen10dp,
                    end = dimen10dp
                )
            ) {
                Row {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        Text(
                            text = stringResource(id = mainState.error)
                        )
                    }
                    Row {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = CLEAR_DIALOG,
                            modifier = Modifier
                                .size(dimen24dp)
                                .clickable {
                                    send(MainEvent.Error(error = null))
                                }
                        )
                    }
                }
            }

        }

        Column (
            modifier = Modifier.padding(top = dimen10dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.request),
                fontSize = 18.sp
            )

            Box(contentAlignment = Alignment.Center) {
                Box{
                    LazyColumnExample(listCard, navController)
                }
                Box {
                    EmptyListImage(listCard.isEmpty())
                }
                Box {

                    if (mainState.loading) return CircularProgressIndicator(
                        color = colorResource(id = R.color.ivory)
                    )
                }


            }

        }

    }

}

@Composable
fun TextFieldExample(
    value: String,
    onValueChanged: (String) -> Unit,
    onCliCk: () -> Unit,
    enabled: Boolean
) {

    val dimen15dp = dimensionResource(id = R.dimen.margin_15)

    TextField(
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = { creditCard(it) },
        trailingIcon = { Icon(Icons.Filled.Search, contentDescription = SEARCH_CARD )},
        placeholder = {
            Text(
                text = (stringResource(id = R.string.edBin))
            )
                      },
        shape = RoundedCornerShape(dimen15dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.ivory),
            unfocusedTextColor = colorResource(id = R.color.ivory),
            disabledTextColor = colorResource(id = R.color.ivory),
            focusedContainerColor = colorResource(id = R.color.pantone),
            unfocusedContainerColor = colorResource(id = R.color.pantone),
            disabledContainerColor = colorResource(id = R.color.pantone),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = colorResource(id = R.color.ivory),
            unfocusedTrailingIconColor = colorResource(id = R.color.ivory),
            disabledTrailingIconColor = colorResource(id = R.color.ivory),
            cursorColor = colorResource(id = R.color.ivory)
        )
    )

    Spacer(modifier = Modifier.padding(top = dimen15dp))

    Button(
        onClick = {
            onCliCk()
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(

        ) ,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_12))
    ) {
        Text(
            text = stringResource(id = R.string.bin),
            fontSize = 18.sp
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyColumnExample(bankCards: List<BankCardItemUI>, navController: NavController){

    val dimen10dp = dimensionResource(id = R.dimen.margin_10)

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.9f)
    ){
      items(
          items = bankCards,
          key = {bankCard -> bankCard.id}
      ){ card ->

          Card(
              border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.ivory)),
              colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.pantone)),
              shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.margin_15)),
              modifier = Modifier
                  .fillMaxWidth(0.8f)
                  .padding(dimen10dp)
                  .animateItemPlacement()
                  .clickable {
                      navController.navigate(route = BankCardScreen.Detail.name + "?id=${card.id}")
                  }
          ){
              Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimen10dp)
              ) {
                  Row(
                      modifier = Modifier
                          .padding(start = dimen10dp)
                          .fillMaxWidth(),

                  ) {
                      Text(
                          text = card.nameBank,
                          fontSize = 18.sp,
                          fontStyle = FontStyle.Italic
                      )
                  }

                  Row(
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(start = dimensionResource(id = R.dimen.margin_30)))
                  {
                      Icon(
                          imageVector = ImageVector.vectorResource(R.drawable.baseline_credit_card_24),
                          tint = colorResource(id = R.color.ivory),
                          modifier = Modifier.size(
                              width = dimensionResource(id = R.dimen.margin_60),
                              height = dimensionResource(id = R.dimen.margin_60)
                          ),
                          contentDescription = CHIP_CARD
                      )
                  }

                  Row(
                      modifier = Modifier
                      .fillMaxWidth(),
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.SpaceAround
                  ) {
                      Text(
                          text = card.number,
                          fontSize = 24.sp
                      )
                  }

                  Row (
                      modifier = Modifier
                          .fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceAround
                  ) {
                      Text(
                          text =card.countryEmoji,
                          fontSize = 24.sp,
                      )
                      Text(
                          text = card.scheme,
                          fontSize = 20.sp,
                          fontStyle = FontStyle.Italic
                      )
                  }
              }
          }


      }
    }
}

@Composable
fun EmptyListImage(visibility: Boolean){
    AnimatedVisibility(visible = visibility) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_credit_card_24),
            contentDescription = EMPTY_LIST,
        )
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BinListTheme {

        MainScreen(
            mainState = MainState(),
            send = {},
            listCard = List(TWO_INT){ index ->
                BankCardItemUI(
                    index.toLong(),
                    NUMBER_CARD,
                    NAME_BANK,
                    SCHEME,
                    COUNTRY_EMOJI
                )
            },
            navController = rememberNavController()
        )
    }
}
