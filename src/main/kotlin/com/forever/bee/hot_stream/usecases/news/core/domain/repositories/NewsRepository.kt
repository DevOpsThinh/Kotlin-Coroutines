/**
 * Kotlin Coroutines: The News Application Example with SharedFlow
 * --------------------------------------------------------------------------------------------------------------------
 *                                               Hot Flows with SharedFlow
 *
 *              <<Producer>>                                             <<Subscribers>>
 *                                                                           _______
 *                                                                |----->   |      |
 *                                                                |         |______|
 *                                                                |
 *                                                                |         _______
 *               Event --------------> SharedFlow ----------------|----->   |     |
 *                                                                |         |     |         [Programming Android with
 *                                                                |         -------         Kotlin (2022 by O'REILLY)]
 *                                                                |         _______
 *                                                                |----->   |     |
 *                                                                          |     |
 *                                                                          -------
 *
 *                                            The News Application Architecture
 *                API
 *                ^
 *         Query  |
 *                |              Get the newsfeed                    Notify the View
 *                Repository <------------------------ View Model ---------------------> View
 *                                                                <---------------------
 *
 * --------------------------------------------------------------------------------------------------------------------
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.hot_stream.usecases.news.core.domain.repositories

import com.forever.bee.hot_stream.usecases.news.core.data.cache.daos.NewsDao
import com.forever.bee.hot_stream.usecases.news.core.domain.model.News
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NewsRepository(private val dao: NewsDao) {
    private val _newsFeed = MutableSharedFlow<News>(replay = 2, 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val newsFeed = _newsFeed.asSharedFlow()

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    init {
        scope.launch {
            while (true) {
                val news = dao.fetchNewsFromApi()
                news.forEach {
                    _newsFeed.emit(it)
                }
                delay(3_000L)
            }
        }
    }

    fun stop() = scope.cancel()
}