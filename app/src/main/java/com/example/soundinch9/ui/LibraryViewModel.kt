package com.example.soundinch9.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundinch9.ui.models.Playlist
import com.example.soundinch9.ui.models.PlaylistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel : ViewModel() {

    private val _selectTab = MutableStateFlow(0)

    val selectedTab: StateFlow<Int> = _selectTab.asStateFlow()

    // filters playlist based on  the selected tab
    val filteredPlaylist: StateFlow<List<Playlist>> = combine(
        PlaylistRepository.playlist, // Source 1 -- The full List
        _selectTab // Source 2 -- The selected tab or active Tab index
    ){
        playlists, tabIndex -> // Runs every time either of the flows changes
        when(tabIndex){
            0 -> playlists   // All playlists
            1 -> playlists.filter { it.isFavorite } // Favorite playlists
            else -> playlists
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun toggleFavorite(playlist: Playlist) = PlaylistRepository.toggleFavorite(playlist)
    fun deletePlaylist(playlist: Playlist) = PlaylistRepository.deletePlaylist(playlist)

    fun onTabSelected(tabIndex: Int) {
        _selectTab.value = tabIndex
    }

}























