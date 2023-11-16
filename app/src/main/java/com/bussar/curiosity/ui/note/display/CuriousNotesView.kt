package com.bussar.curiosity.ui.note.display

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bussar.curiosity.R
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.ui.note.create.CreateCuriousNote
import com.bussar.curiosity.ui.note.create.CuriousNotesViewModel
import com.bussar.curiosity.ui.theme.Dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CuriousNotesView(
    viewModel: CuriousNotesViewModel,
) {
    val curiotes by viewModel.notes.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.setSheetValue(true)
                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            stickyHeader {
                Text(text = stringResource(id = R.string.curiotesList))
            }
            items(curiotes) { item: CuriousNote ->
                CuriousNoteItem(curiousNote = item)
            }
        }
    }
    CreateCuriousNote(viewModel = viewModel)
}

@Composable
fun CuriousNoteItem(curiousNote: CuriousNote) {
    Card() {
        Column(modifier = Modifier.padding(Dimens.Padding.paddingDefault)) {
            curiousNote.title?.let { Text(text = curiousNote.title) }
            curiousNote.note?.let { Text(text = curiousNote.note) }
            //curiousNote.links?.isNotEmpty()?.let { Text(text = curiousNote.links.first().link) }
        }
    }
}