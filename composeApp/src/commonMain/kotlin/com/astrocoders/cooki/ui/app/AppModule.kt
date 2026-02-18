package com.astrocoders.cooki.ui.app

import com.astrocoders.cooki.data.repository.ExampleRepositoryImpl
import com.astrocoders.cooki.domain.repository.ExampleRepository
import com.astrocoders.cooki.domain.usecase.GetExampleUseCase
import com.astrocoders.cooki.ui.navigation.Navigator
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single { Navigator() }

    // Repositories
    single<ExampleRepository> {
        ExampleRepositoryImpl()
    }

    factory { GetExampleUseCase(get())  }



  /*  // UseCases
    factory {
        GetRecipesUseCase(get())
    }*/

   /* // Database
    single {
        AppDatabase(get())
    }*/

   /* // Ktor client
    single {
        HttpClientFactory.create()
    }*/
}

fun initKoin(): KoinApplication =
    startKoin {
        modules(appModule)
    }