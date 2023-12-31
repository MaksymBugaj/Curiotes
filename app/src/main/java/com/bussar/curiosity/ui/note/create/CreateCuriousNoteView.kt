package com.bussar.curiosity.ui.note.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bussar.curiosity.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCuriousNote(
    viewModel: CuriousNotesViewModel,
) {
    val sheetState = rememberModalBottomSheetState()
    val isSheetOpen by viewModel.isSheetOpen.collectAsStateWithLifecycle()
    val showSavingError by viewModel.showSavingError.collectAsStateWithLifecycle()

    if (isSheetOpen)
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { viewModel.setSheetValue(false) },
            modifier = Modifier.padding(Dimens.Padding.paddingDefault)
        ) {
            CreateCuriousNoteContent(
                viewModel = viewModel,
                showError = showSavingError
                )
        }
}

@Composable
fun ErrorToast(
    textValue: String = "At Least one field required!"
) {
    Text(
        text = textValue.uppercase(),
        modifier = Modifier,
        color = Color.Red,

    )
}

@Composable
fun CreateCuriousNoteContent(
    viewModel: CuriousNotesViewModel,
    showError: Boolean
) {
    val noteTitle by viewModel.noteTitle.collectAsStateWithLifecycle()
    val noteDescription by viewModel.noteDescription.collectAsStateWithLifecycle()
    val noteLink by viewModel.noteLink.collectAsStateWithLifecycle()
    val needsDetails by viewModel.needsDetailedExplanation.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Padding.paddingDefault)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Create Curiote!")
            IconButton(onClick = {
                viewModel.saveClicked()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        }
        if(showError) ErrorToast()
        OutlinedTextField(
            value = noteTitle,
            onValueChange = viewModel::setNoteTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.Padding.paddingDefault),
            label = { Text(text = "Title")}
        )
        OutlinedTextField(
            value = noteDescription,
            onValueChange = viewModel::setNoteDescription,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.Padding.paddingDefault),
            label = { Text(text = "Description")}
        )
        OutlinedTextField(
            value = noteLink,
            onValueChange = viewModel::setNoteLink,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.Padding.paddingDefault),
            label = { Text(text = "Link")}
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.Padding.paddingDefault),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Mark For future extended description")
            Switch(
                checked = needsDetails,
                onCheckedChange = viewModel::setDetailedExplanation,
                modifier = Modifier.padding(start = Dimens.Padding.paddingDefault),
                thumbContent = if (needsDetails) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )
        }



        Spacer(modifier = Modifier.height(Dimens.Padding.paddingXLarge))
    }
}