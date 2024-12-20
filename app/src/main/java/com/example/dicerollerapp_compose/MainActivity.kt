package com.example.fuelcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.fuelcalculatorapp.ui.theme.FuelCalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelCalculatorAppTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FuelCalculatorApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun FuelCalculatorApp() {
    FuelCalculatorScreen(modifier = Modifier.wrapContentSize(Alignment.Center))
}

@Composable
fun FuelCalculatorScreen(modifier: Modifier = Modifier) {
    var percentage by remember { mutableStateOf(0.7f) } // Default is 70%
    var gasolinePrice by remember { mutableStateOf(0f) }
    var alcoholPrice by remember { mutableStateOf(0f) }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select the percentage for alcohol profitability:")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { percentage = 0.7f }) {
            Text("70%")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { percentage = 0.75f }) {
            Text("75%")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Gasoline Price: $\${gasolinePrice}")
        Text(text = "Alcohol Price: $\${alcoholPrice}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Sample logic for testing (replace with actual input handling)
            gasolinePrice = (1..10).random() * 0.1f // Simulated price
            alcoholPrice = (1..10).random() * 0.1f // Simulated price

            val threshold = gasolinePrice * percentage
            resultText = if (alcoholPrice <= threshold) {
                "Alcohol is the better option."
            } else {
                "Gasoline is the better option."
            }
        }) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultText, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun FuelCalculatorScreenPreview() {
    FuelCalculatorAppTheme {
        FuelCalculatorScreen()
    }
}
