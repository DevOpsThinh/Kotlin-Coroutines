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
package com.forever.bee.hot_stream.usecases.news.newsList.presentation

import com.forever.bee.hot_stream.usecases.news.core.domain.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

// import androidx.lifecycle.ViewModel
// import com.forever.bee.hot_stream.usecases.news.core.domain.model.News

//class NewsViewModel(private val repo: NewsRepository): ViewModel() {
//    private val newsList = mutableListOf<News>()
//
//    private val _newsLiveData = MutableLiveData<List<News>>(newsList)
//    val newsLiveData: LiveData<List<News>> = _newsLiveData
//
//    init {
//        viewModelScope.launch {
//            repo.newsFeed.collect {
//                println("NewsViewModel receives $it")
//                newsList.add(it)
//                _newsLiveData.value = newsList
//            }
//        }
//    }
//}

class NewsViewsModel(private val repository: NewsRepository) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    init {
        viewModelScope.launch {
            repository.newsFeed.collect {
                println("NewsViewsModel receives $it")
            }
        }
    }
}