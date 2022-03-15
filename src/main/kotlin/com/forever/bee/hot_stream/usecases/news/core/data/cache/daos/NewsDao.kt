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
package com.forever.bee.hot_stream.usecases.news.core.data.cache.daos

import com.forever.bee.hot_stream.usecases.news.core.domain.model.News

interface NewsDao {
    suspend fun fetchNewsFromApi(): List<News>
}