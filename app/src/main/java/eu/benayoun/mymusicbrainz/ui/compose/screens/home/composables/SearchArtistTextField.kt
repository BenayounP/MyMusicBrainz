package eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding2
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding4
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding8


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchArtistTextField(modifier: Modifier = Modifier, onButtonClick: (query: String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding8)
            .height(IntrinsicSize.Min)
    ) {

        val maxCharInTextField = 30
        val keyboardController = LocalSoftwareKeyboardController.current

        fun search() {
            keyboardController?.hide()
            onButtonClick(textState.value.text)
        }

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(end = padding4)
                .wrapContentSize(),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onSecondary),
            label = { Text("Search artist") }, // todo replace with a string in xml
            placeholder = { Text("Artist name") }, // todo replace with a string in xml
            value = textState.value,
            onValueChange = { if (it.text.length <= maxCharInTextField) textState.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { search() })
        )
        Button(modifier = Modifier
            .wrapContentSize()
            .padding(start = padding4, top = padding8)
            .fillMaxHeight(),
            shape = RoundedCornerShape(padding2),
            onClick = {
                search()
            }) {
            Text("SEARCH") // todo replace with a string in xml
        }
    }

}