package com.wstore.engine
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
class MainActivity: ComponentActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContent{Text("W-StoreMgData")}}}