package com.example.soundinch9.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.soundinch9.ui.models.Playlist
import com.example.soundinch9.ui.theme.SoundInCh9Theme

@Composable
fun PlaylistCard(
    playlist: Playlist,
    onClick : () -> Unit,
    onLongClick : () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            //Color cover generated from the model's HEX string
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(playlist.colorHex.toColorInt())),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = "Playlist cover",
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.size(32.dp)
                )
            }
            // Playlist Information
            Column(modifier = Modifier.padding(8.dp)) {
                // Challenge Text for the playlist name
                Text(
                    text = playlist.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                // Challenge Text for the number of songs
                Text(
                    text = "${playlist.songCount} songs",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaylistCardPreview() {
    SoundInCh9Theme{
        Box(modifier = Modifier.padding(16.dp)){
            PlaylistCard(
                playlist = Playlist(
                    id = 1,
                    name = "An all-time favorite is the one that makes you happy",
                    genre = "Rock",
                    songCount = 15,
                    colorHex = "#E91E63"
                ),
                onClick = {},
                onLongClick = {}
            )
        }
    }
}












