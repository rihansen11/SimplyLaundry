package org.examle.simplylaundry.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.examle.simplylaundry.R
import org.examle.simplylaundry.navigation.Screen


@Composable
fun IconPicker(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreen(navController: NavHostController, onNavigationToHistory: (String) -> Unit) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var nameError by rememberSaveable {
        mutableStateOf(false)
    }
    var kiloan by rememberSaveable {
        mutableStateOf("")
    }
    var kiloanError by rememberSaveable {
        mutableStateOf(false)
    }



    val radioOptions = listOf(
        stringResource(id = R.string.regular),
        stringResource(id = R.string.expres),
        stringResource(id = R.string.fast)
    )

    var jenisPelayanan by rememberSaveable {
        mutableStateOf(radioOptions[0])
    }
    var pelayananError by rememberSaveable {
        mutableStateOf(false)
    }
    var result by rememberSaveable {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current


    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Screen.History.route}/{text}")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_history_24),
                            contentDescription = stringResource(id = R.string.history))
                    }
                    IconButton(
                        onClick = { navController.navigate(Screen.About.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi)
                        )
                    }

                }
            )
        }
    ){ paddingValues->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
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
                },
                isError = nameError,
                trailingIcon = { IconPicker(nameError, "")},
                supportingText = { ErrorHint(nameError)}

            )
            OutlinedTextField(
                value = kiloan,
                onValueChange ={
                    kiloan = it
                },
                label = {
                    Text(text = stringResource(id = R.string.jumlah_kiloan))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = kiloanError,
                trailingIcon = { IconPicker(kiloanError, "Kg")},
                supportingText = { ErrorHint(kiloanError)}
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                radioOptions.forEach{option->
                    RadioOptions(isSelected = jenisPelayanan == option, label = option,


                        modifier = Modifier
                        .selectable(
                            selected = jenisPelayanan == option,
                            onClick = { jenisPelayanan = option },
                            role = Role.RadioButton
                        )
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 10.dp),


                    )
                }
            }

            Row(modifier = Modifier.padding(top = 10.dp)){
                Button(
                    onClick = {
                        onNavigationToHistory(resultText(name,kiloan.toInt(),jenisPelayanan, result))
                        name = ""
                        kiloan = ""
                        jenisPelayanan[0]
                        result = 0
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.tambahkan_list))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        shareData(context, resultText(name,kiloan.toInt(),jenisPelayanan,result))
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)

                ) {
                    Text(text = stringResource(id = R.string.bagikan))
                }

            }
            Button(onClick = {

                nameError = (name == "")
                kiloanError = (kiloan == "" || kiloan == "0")
                pelayananError = (jenisPelayanan == "")

                if(nameError || kiloanError || pelayananError){
                    return@Button
                } else {
                    result = getJenisPelayanan(jenisPelayanan = jenisPelayanan, kiloan =kiloan.toInt() )
                }




            }

            ) {
                Text(text = "Tampilkan Hasilnya")
            }
            if (0 != result){
                Text(text = resultText(name,kiloan.toInt(),jenisPelayanan,result))
            }
        }
    }
}
@Composable
fun RadioOptions(
    isSelected : Boolean,
    label: String,
    modifier: Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Text(text = label)
        Spacer(modifier = Modifier.width(6.dp))
        RadioButton(selected = isSelected, onClick = null)
    }
}

fun getJenisPelayanan(jenisPelayanan: String, kiloan:Int) : Int{
    return when(jenisPelayanan){
        "Reguler (3 hari) Rp.6000" -> 6000 * kiloan
        "Express (6 jam) Rp.15.000" -> 15000 * kiloan
        "Fast (3 jam) Rp.20.000" -> 20000 * kiloan
        else -> 6000 * kiloan
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

fun resultText(nama:String,kiloan: Int,jenisPelayanan: String, result: Int) : String{
    return "$nama dengan $kiloan kg dalam $jenisPelayanan maka hasil harga $result"
}