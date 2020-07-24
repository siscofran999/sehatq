package com.siscofran.sehatq.di

import com.siscofran.sehatq.data.ApiHelper
import com.siscofran.sehatq.data.ApiRepository
import com.siscofran.sehatq.data.ProductHelper
import com.siscofran.sehatq.data.ProductRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsApiRepository(repository: ApiRepository): ApiHelper

    @Binds
    abstract fun bindsDbRepository(repository: ProductRepository) : ProductHelper
}