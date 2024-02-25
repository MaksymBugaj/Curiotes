package com.bussar.curiosity.ui.note.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bussar.curiosity.R
import com.bussar.curiosity.ui.theme.Dimens

@Composable
fun CreateCuriousNote(
    viewModel: CreateCuriousNotesViewModel,
    onSaved: () -> Unit,
) {
    val showSavingError by viewModel.showCreateErrorTest.collectAsStateWithLifecycle(initialValue = false)

    CreateCuriousNoteContent(
        viewModel = viewModel,
        showError = showSavingError,
        onSaved = onSaved
    )

}

@Composable
fun ErrorToast(
    textValue: String = stringResource(id = R.string.errorToastMinimumField),
) {
    Text(
        text = textValue.uppercase(),
        modifier = Modifier,
        color = Color.Red,

        )
}

@Composable
fun CreateCuriousNoteContent(
    viewModel: CreateCuriousNotesViewModel,
    showError: Boolean,
    onSaved: () -> Unit,
) {
    val noteTitle by viewModel.noteTitle.collectAsStateWithLifecycle()
    val noteDescription by viewModel.noteDescription.collectAsStateWithLifecycle()
    val noteLink by viewModel.noteLink.collectAsStateWithLifecycle()
    val needsDetails by viewModel.needsDetailedExplanation.collectAsStateWithLifecycle()
    val enableSaveButton by viewModel.enableSaveButton.collectAsStateWithLifecycle()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Padding.paddingDefault)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.createCurioteTitle))
                IconButton(onClick = {
                    viewModel.saveClicked()
                    onSaved()
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
                }
            }
            if (showError) ErrorToast()
            OutlinedTextField(
                value = noteTitle,
                onValueChange = viewModel::setNoteTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.Padding.paddingDefault),
                label = { Text(text = stringResource(id = R.string.titleLabel)) }
            )
            OutlinedTextField(
                value = noteDescription,
                onValueChange = viewModel::setNoteDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.Padding.paddingDefault),
                label = { Text(text = stringResource(id = R.string.descriptionLabel)) }
            )
            OutlinedTextField(
                value = noteLink,
                onValueChange = viewModel::setNoteLink,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.Padding.paddingDefault),
                label = { Text(text = stringResource(id = R.string.link)) }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.Padding.paddingDefault),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.createCurioteCheckLaterLabel))
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.saveClicked()
                        onSaved()
                    },
                    enabled = enableSaveButton,
                    modifier = Modifier
                ) {
                    Text(text = "Save")
                }
            }

            Spacer(modifier = Modifier.height(Dimens.Padding.paddingXLarge))
        }
    }
}