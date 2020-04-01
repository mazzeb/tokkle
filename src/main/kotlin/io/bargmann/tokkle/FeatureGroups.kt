package io.bargmann.tokkle

import org.togglz.core.annotation.EnabledByDefault
import org.togglz.core.annotation.FeatureGroup
import org.togglz.core.annotation.Label

@FeatureGroup
@Label("Emergency")
annotation class Emergency

@FeatureGroup
@Label("In Development")
annotation class InDevelopment
