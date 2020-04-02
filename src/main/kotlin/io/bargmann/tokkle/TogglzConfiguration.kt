package io.bargmann.tokkle

import com.mongodb.MongoClient
import com.mongodb.WriteConcern
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.togglz.core.context.StaticFeatureManagerProvider
import org.togglz.core.manager.FeatureManager
import org.togglz.core.manager.FeatureManagerBuilder
import org.togglz.core.repository.StateRepository
import org.togglz.core.spi.FeatureProvider
import org.togglz.core.user.UserProvider
import org.togglz.kotlin.EnumClassFeatureProvider
import org.togglz.mongodb.MongoStateRepository

@Configuration
@EnableConfigurationProperties(MongoProperties::class)
class TogglzConfiguration(private val mongoProperties: MongoProperties) {

    @Bean
    fun featureProvider() = EnumClassFeatureProvider(Features::class.java)

    @Bean
    fun stateRepository(mongoClient: MongoClient): StateRepository {
        return MongoStateRepository
                .newBuilder(mongoClient, "someDatabase")
                .collection("features")
                .writeConcern(WriteConcern.ACKNOWLEDGED)
                .build()
    }

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
