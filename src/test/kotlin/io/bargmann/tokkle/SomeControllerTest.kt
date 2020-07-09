package io.bargmann.tokkle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.togglz.core.Feature
import org.togglz.kotlin.FeatureManagerSupport

class SomeControllerTest {

    private lateinit var mockMvc: MockMvc

    private val featureManager = FeatureManagerSupport.createFeatureManagerForTest(Features::class)

    @BeforeEach
    fun setUp() {
        mockMvc = standaloneSetup(SomeController()).build()
        FeatureManagerSupport.enableAllFeatures(featureManager)
    }

    @Test
    fun `all features should be active`() {

        // when
        val mvcResult = mockMvc.get("/").andReturn()

        // then
        assertThat(mvcResult.response.contentAsString).isEqualTo("feature one is active")
    }

    @Test
    fun `feature one should be inactive`() {
        FeatureManagerSupport.disable(Feature { Features.FEATURE_ONE.name })

        // when
        val mvcResult = mockMvc.get("/").andReturn()

        // then
        assertThat(mvcResult.response.contentAsString).isEqualTo("i am the default without active features")
    }
}
