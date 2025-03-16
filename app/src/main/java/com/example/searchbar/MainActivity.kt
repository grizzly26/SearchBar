package com.example.searchbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.searchbar.ui.theme.SearchBarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val searchText = remember {
                mutableStateOf("")
            }
            val mainList = remember {
                mutableStateOf(Utils.originUserList)
            }
            val isActive = remember {
                mutableStateOf(false)
            }
            SearchBarTheme {
                Column(modifier = Modifier
                    .fillMaxSize()) {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        query = searchText.value,
                        onQueryChange = { text ->
                            searchText.value = text
                            mainList.value = Utils.search(text)
                        },
                        onSearch = { text ->
                            mainList.value = Utils.search(text)

                        },
                        placeholder = {
                            Text("Search....")
                        },
                        active = isActive.value,
                        onActiveChange = {
                            isActive.value = it
                        },
                    ) {
                        LazyColumn {
                            items(mainList.value) { item ->
                                Box(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .clickable {
                                            isActive.value = false
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object Utils{
  val originUserList = listOf("" +
            "Andrey",
            "Serega",
            "Anton",
            "Vladimir",
            "Arsene",
            "Ilya")

    fun search(text:String):List<String>{
        return originUserList.filter {
            it.lowercase().startsWith(text.lowercase())
        }
    }
}

