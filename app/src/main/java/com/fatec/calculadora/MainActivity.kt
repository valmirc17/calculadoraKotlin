package com.fatec.calculadora
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fatec.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculadoraScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    val pastelColors = listOf(
        Color(0xFFFF5F70),
        Color(0xFF64A5FF),
        Color(0xFFC4FF6D),
        Color(0xFFFFC66B),
        Color(0xFF9160FF)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Calculadora", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3C3C3C))

        Spacer(modifier = Modifier.height(32.dp))

        // Input for number 1
        TextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Primeiro valor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF3F3F3),
                focusedIndicatorColor = pastelColors[1]
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input for number 2
        TextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Segundo valor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF3F3F3),
                focusedIndicatorColor = pastelColors[2]
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val operationButtons = listOf("+", "-", "×", "÷")
            operationButtons.forEachIndexed { index, operation ->
                Button(
                    onClick = {
                        val num1 = number1.toFloatOrNull()
                        val num2 = number2.toFloatOrNull()

                        if (num1 != null && num2 != null) {
                            result = when (operation) {
                                "+" -> (num1 + num2).toString()
                                "-" -> (num1 - num2).toString()
                                "×" -> (num1 * num2).toString()
                                "÷" -> if (num2 != 0f) (num1 / num2).toString() else "Erro: Divisão por zero"
                                else -> ""
                            }
                        } else {
                            result = "Insira valores válidos"
                        }
                    },
                    modifier = Modifier.weight(1f).padding(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = pastelColors[index])
                ) {
                    Text(operation, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                number1 = ""
                number2 = ""
                result = null
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = pastelColors[4])
        ) {
            Text("Limpar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        result?.let {
            Text(
                text = "Resultado: $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculadoraTheme {
        CalculadoraScreen()
    }
}
