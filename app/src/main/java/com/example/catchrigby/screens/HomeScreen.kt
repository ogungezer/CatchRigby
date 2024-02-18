package com.example.catchrigby.screens



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catchrigby.R
import com.example.catchrigby.ui.theme.background


@Composable
fun HomeScreen() {
    var isStarted by rememberSaveable { mutableStateOf(false) }

    if (isStarted) {
        GameScreen()
    }
    else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = background)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.rigby_homescreen),
                    contentDescription = "rigby",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(380.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    isStarted = true
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    focusedElevation = 8.dp
                ),
                border = BorderStroke(4.dp, color = Color(0xFFA25900)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B2318)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "PLAY", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            }
        }
    }
}



