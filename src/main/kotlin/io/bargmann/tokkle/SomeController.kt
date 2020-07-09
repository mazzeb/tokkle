package io.bargmann.tokkle

import io.bargmann.tokkle.Features.FEATURE_ONE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeController {

    @GetMapping("/")
    fun somePage() : String {

        return if (FEATURE_ONE.isActive()) {
            "feature one is active"
        } else {
            "i am the default without active features"
        }
    }
}
