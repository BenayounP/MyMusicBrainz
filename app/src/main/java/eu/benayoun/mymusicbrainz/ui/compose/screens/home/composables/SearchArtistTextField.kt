package eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import eu.benayoun.mymusicbrainz.ui.theme.ComposeDimensions.padding8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchArtistTextField(modifier: Modifier = Modifier, onButtonClick: (query: String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Row(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = padding8)
                .weight(1f),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.primary),
            label = { Text("Search artist") }, // todo replace with a string in xml
            placeholder = { Text("Artist name") }, // todo replace with a string in xml
            value = textState.value,
            onValueChange = { textState.value = it }
        )
        Button(modifier = Modifier.wrapContentSize(),
            onClick = { onButtonClick(textState.value.text) }) {
            Text("Search")
        }
    }

}