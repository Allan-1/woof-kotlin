package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.DataSource
import com.example.woof.model.Dog
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { WoofTopBar() }
                ) {

                    DogColumn(woof = DataSource().dogs)
                }
            }
        }
    }
}

@Composable
fun WoofTopBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier.background(color = MaterialTheme.colors.primary).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.ic_woof_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name)
        )
    }
}

@Composable
fun DogColumn(woof: List<Dog>){
    LazyColumn{
        items(woof){
            dog -> DogCard(dog = dog)
        }
    }
}

@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier){
    Image( modifier = modifier
        .size(64.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = dogIcon),
        contentDescription = null)
}

@Composable
fun DogCard(dog: Dog, modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            DogIcon(dogIcon = dog.imageResourceId)
            Column() {
                Text(text = stringResource(id = dog.stringResourceId),
                    modifier = modifier.padding(top = 8.dp),)
                Text(text = stringResource(id = R.string.years_old, dog.age))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoofTheme {
        DogColumn(woof = DataSource().dogs)
    }
}