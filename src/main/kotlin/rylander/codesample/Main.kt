package rylander.codesample

import rylander.codesample.local.LocalApp

fun main(args: Array<String>) {
    if (args.size >= 3 && args[0] == "local") {
        LocalApp().run(args)
    }
}
