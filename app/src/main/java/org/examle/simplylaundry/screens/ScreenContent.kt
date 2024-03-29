package org.examle.simplylaundry.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.examle.simplylaundry.R

@Composable

fun ScreenContent(modifier: Modifier) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var nameError by remember {
        mutableStateOf(false)
    }
    var kiloan by rememberSaveable {
        mutableStateOf("")
    }
    var kiloanError by remember {
        mutableStateOf(false)
    }
    var harga by rememberSaveable {
        mutableStateOf("")
    }
    var hargaError by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(20.dp))
            Image(painter = painterResource(id = R.drawable.laundry_machine), contentDescription = stringResource(
                id = R.string.logo
            ), modifier = Modifier
                .height(100.dp)
                .width(100.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(id = R.string.desc), fontSize = 13.5.sp, textAlign = TextAlign.Center)
        }
        OutlinedTextField(
            value = name,
            onValueChange ={
                name = it
            },
            label = {
                Text(text = stringResource(id = R.string.nama_anda))
            }
        )
        OutlinedTextField(
            value = kiloan,
            onValueChange ={
                kiloan = it
            },
            label = {
                Text(text = stringResource(id = R.string.jumlah_kiloan))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.jenis_baju),
            fontSize = 13.5.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.masukan_harga),
            fontSize = 13.5.sp, textAlign = TextAlign.Center)

        OutlinedTextField(
            value = harga,
            onValueChange ={
                harga = it
            },
            label = {
                Text(text = stringResource(id = R.string.masuk_harga))
            }
        )
        Button(
            onClick = { },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
            Text(text = stringResource(id = R.string.mulai))
        }
        Button(
            onClick = {
                shareData(
                    context = context,
                    message = context.getString(R.string.bagikan_template,


                ))
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)

        ) {
            Text(text = stringResource(id = R.string.bagikan))
        }

        Divider(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
            thickness = 1.dp
        )

        
    }
}
fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

