package com.example.wordcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wordcounter.ui.theme.WordCounterTheme

class MainActivity : ComponentActivity() {
    private val wordCounter = WordCounter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WordCounterScreen(
                        wordCounter = wordCounter,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WordCounterScreen(
    wordCounter: WordCounter,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    val statistics = remember(textState.text) {
        wordCounter.getStatistics(textState.text)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Word Counter",
            style = MaterialTheme.typography.headlineLarge
        )
        
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            label = { Text("Enter your text here") },
            placeholder = { Text("Type or paste your text...") },
            maxLines = 10
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Statistics",
                    style = MaterialTheme.typography.titleLarge
                )
                
                StatisticRow("Words", statistics.wordCount.toString())
                StatisticRow("Characters", statistics.characterCount.toString())
                StatisticRow("Characters (no spaces)", statistics.characterCountWithoutSpaces.toString())
                StatisticRow("Sentences", statistics.sentenceCount.toString())
                StatisticRow("Paragraphs", statistics.paragraphCount.toString())
            }
        }
    }
}

@Composable
fun StatisticRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WordCounterScreenPreview() {
    WordCounterTheme {
        WordCounterScreen(wordCounter = WordCounter())
    }
}