/**
 * Kotlin Coroutines: A resource sample described earlier could be implemented using the new API.
 * References: https://blog.jetbrains.com/kotlin/2020/10/kotlinx-coroutines-1-4-0-introducing-stateflow-and-sharedflow/
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.hot_stream

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.*
//import kotlinx.coroutines.flow.*
//
//sealed class SharedViewEffects {
//    data class Prices(val prices: Int): SharedViewEffects()
//}
//
//class SalePriceSharedViewModel: ViewModel() {
//    private val _shareViewEffects = MutableSharedFlow<SharedViewEffects>()
//
//    val sharedViewEffects: SharedFlow<SharedViewEffects> = _shareViewEffects.asSharedFlow()
//
//    init {
//        getPrices()
//    }
//
//    private fun getPrices() {
//        viewModelScope.launch {
//            for (i in 1..100) {
//                delay(5000L)
//                ensureActive()
//                _shareViewEffects.emit(SharedViewEffects.Prices(i))
//            }
//        }
//    }
//}
//
//class DownloadingModel {
//
//    private val _state = MutableStateFlow<DownloadStatus>(DownloadStatus.NOT_REQUESTED)
//    val state: StateFlow<DownloadStatus> get() = _state
//
//    suspend fun download() {
//        _state.value = DownloadStatus.INITIALIZED
//        initializeConnection()
//        processAvailableContent {
//                partialData: ByteArray,
//                downloadedBytes: Long,
//                totalBytes: Long
//            ->
//            storePartialData(partialData)
//            _state.value = DownloadStatus.IN_PROGRESS
//        }
//        _state.value = DownloadStatus.SUCCESS
//    }
//}