package io.bargmann.tokkle

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.togglz.core.context.StaticFeatureManagerProvider
import org.togglz.core.manager.FeatureManager
import org.togglz.core.manager.FeatureManagerBuilder
import org.togglz.core.repository.StateRepository
import org.togglz.core.spi.FeatureProvider
import org.togglz.core.user.UserProvider
import org.togglz.kotlin.EnumClassFeatureProvider

@Configuration
class TogglzConfiguration  {

    @Bean
    fun featureProvider() = EnumClassFeatureProvider(Features::class.java)

    @Bean
    fun featureManager(stateRepository: StateRepository,
                         userProvider: UserProvider,
                         featureProvider: FeatureProvider): FeatureManager {

        val featureManager = FeatureManagerBuilder()
                .featureProvider(featureProvider)
                .stateRepository(stateRepository)
                .userProvider(userProvider)
                .build()

        StaticFeatureManagerProvider.setFeatureManager(featureManager)
        return featureManager
    }
}
