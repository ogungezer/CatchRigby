package com.example.catchrigby.screens

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catchrigby.R
import com.example.catchrigby.ui.theme.TimeColor
import com.example.catchrigby.ui.theme.background
import com.example.catchrigby.ui.theme.robotoStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun GameScreen() {
    var time by rememberSaveable { mutableStateOf(30)}
    var isPlaying by rememberSaveable { mutableStateOf(true)}
    var score by rememberSaveable { mutableStateOf(0)}
    var xCoordinate by rememberSaveable { mutableStateOf<Int?>(null)}
    var yCoordinate by rememberSaveable { mutableStateOf<Int?>(null)}
    var isClicked by rememberSaveable { mutableStateOf(false)}
    var openAlertDialog by rememberSaveable { mutableStateOf(false)}
    var goHomeScreen by rememberSaveable { mutableStateOf<Boolean?>(null)}
    val rigbySize = 140

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    //val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val partyTonightAudio : MediaPlayer = MediaPlayer.create(context, (R.raw.party_tonight))
    val rigbyScream : MediaPlayer = MediaPlayer.create(context,R.raw.rigby_screaming)

    LaunchedEffect(key1 = isPlaying){
        launch {
            while(isPlaying){
                if (isClicked) {
                    rigbyScream.start()
                    score++
                    isClicked = false
                }
                xCoordinate = ((8 + rigbySize/20)..(screenWidth - rigbySize - 8)).random()
                yCoordinate = ((rigbySize/20)..(520 - rigbySize)).random()
                delay(300)
            }
        }

        launch {
            while(isPlaying) {
                partyTonightAudio.start()
                delay(1000)
                time -= 1
                if(time == 0){
                    partyTonightAudio.stop()
                    isPlaying = false
                    openAlertDialog = true
                }
            }
        }
    }

    if(openAlertDialog){
        isPlaying = false
        GameAlertDialog(score = score, onDismissClicked = { goHomeScreen = true }, onConfirmClicked = {goHomeScreen = false })
        if(goHomeScreen == true){
            time = 30
            score = 0
            openAlertDialog = false
        }
        else if(goHomeScreen == false){
            time = 30
            score = 0
            isPlaying = true
            openAlertDialog = false
            goHomeScreen = null
        }
    }
    if(goHomeScreen == true){
        HomeScreen()
    }
    else {
        Column(
            modifier = Modifier
                .background(color = background)
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Time: $time",
                    style = robotoStyle,
                    fontWeight = FontWeight.Normal,
                    color = TimeColor,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Divider(
                color = TimeColor,
                modifier = Modifier.padding(start = 140.dp, end = 140.dp, top = 2.dp)
            )
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = "Score: $score",
                style = robotoStyle,
                fontWeight = FontWeight.Bold,
                color = TimeColor,
                fontSize = 35.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(520.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.rigby),
                        contentDescription = "catch rigby",
                        modifier = Modifier
                            .size(rigbySize.dp)
                            .offset(xCoordinate?.dp ?: 0.dp, yCoordinate?.dp ?: 0.dp)
                            .clickable { isClicked = true }
                    )
                }
            }
        }
    }
}

@Composable
fun GameAlertDialog(
    score : Int,
    onDismissClicked : () -> Unit,
    onConfirmClicked : () -> Unit
) {
    AlertDialog(
        onDismissRequest = {onDismissClicked()},
        dismissButton = {
            Button(
                onClick = onDismissClicked
            ){
                Text(text = "No")
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirmClicked
            ){
                Text(text = "Play again")
            }
        },
        title = { Text(text = "Your score is $score")},
        text = { Text(text = "Do you want to play again?")},

    )
}

