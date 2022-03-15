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
package com.forever.bee.hot_stream.usecases.news

import com.forever.bee.hot_stream.usecases.news.core.data.cache.daos.NewsDao
import com.forever.bee.hot_stream.usecases.news.core.domain.model.News
import com.forever.bee.hot_stream.usecases.news.core.domain.repositories.NewsRepository
import com.forever.bee.hot_stream.usecases.news.newsList.presentation.NewsViewsModel
import com.forever.bee.hot_stream.usecases.news.topNews.presentation.TopNewsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val dao = object : NewsDao {
        private var index = 0

        override suspend fun fetchNewsFromApi(): List<News> {
            delay(2_00L)
            return listOf(News("news content ${index++}"), News("news content ${index++}"))
        }
    }

    val repository = NewsRepository(dao)
    NewsViewsModel(repository)
    delay(1_00L)
    TopNewsViewModel(repository)
    delay(30_000L)
    repository.stop()
}