package com.siscofran.sehatq.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siscofran.sehatq.ViewModelProviderFactory
import com.siscofran.sehatq.ui.detail.DetailViewModel
import com.siscofran.sehatq.ui.history.HistoryViewModel
import com.siscofran.sehatq.ui.main.home.HomeViewModel
import com.siscofran.sehatq.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory?
}
