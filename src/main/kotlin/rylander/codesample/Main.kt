package rylander.codesample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import rylander.codesample.local.LocalApp

fun main(args: Array<String>) {
    if (args.size >= 3 && args[0] == "local") {
        LocalApp().run(args)
    } else if (args[0] == "server") {
        SpringApplication.run(WebApp::class.java, *args)
    }
}

@SpringBootApplication
class WebApp