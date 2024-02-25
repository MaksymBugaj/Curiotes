package com.bussar.curiosity.ui.note.display

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bussar.curiosity.R
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.ui.theme.Dimens
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CuriousNotesView(
    viewModel: CuriousNotesViewModel,
    onCreateCurioteClick: () -> Unit
) {
    val curiotes by viewModel.notes.collectAsStateWithLifecycle()

    val paddingDefault = Dimens.Padding.paddingDefault

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateCurioteClick,
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
                    top = paddingDefault,
                    end = paddingDefault,
                    start = paddingDefault,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            stickyHeader {
                Text(text = stringResource(id = R.string.curiotesList))
            }
            items(
                items = curiotes,
                key = {
                    it.id
                }
            ) { item: CuriousNote ->
                CuriousNoteItem(curiousNote = item, onCurioteClick = {
                    //todo viewModel::editCuriote
                })
            }
        }
    }
}

@Composable
fun CuriousNoteItem(
    curiousNote: CuriousNote,
    onCurioteClick: (curiote: CuriousNote) -> Unit,
) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable { onCurioteClick(curiousNote) }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Dimens.Padding.paddingDefault)
        ) {
            curiousNote.title?.let {
                Text(
                    text = curiousNote.title,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            curiousNote.note?.let {
                Text(
                    text = curiousNote.note,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                    )
            }
            curiousNote.modifiedAt?.let {
                Text(
                    text = curiousNote.modifiedAt.toFullDateString(),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }

            if(!curiousNote.links.isNullOrEmpty())  {
                curiousNote.links.map { link ->
                    Text(
                        text = link.link,
                        modifier = Modifier.padding(Dimens.Padding.paddingDefault)
                    )
                }
            }
        }
    }
}

//todo move to utils
fun ZonedDateTime.toFullDateString() : String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}