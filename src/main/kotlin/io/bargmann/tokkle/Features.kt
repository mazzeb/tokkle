package io.bargmann.tokkle

import org.togglz.core.annotation.EnabledByDefault
import org.togglz.core.annotation.Label
import org.togglz.core.context.FeatureContext

enum class Features  {

    @Label("The feature number one")
    @InDevelopment
    FEATURE_ONE,

    @Label("Feature number two")
    @InDevelopment
    FEATURE_TWO,

    @Emergency
    @Label("another feature")
    @EnabledByDefault
    FEATURE_THREE;

    fun isActive() : Boolean = FeatureContext.getFeatureManager().isActive { name }
}

