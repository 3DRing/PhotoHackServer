package com.ringov.generator

class Generator {
    val elements = listOf<String>(
            "вы будете в шоке, когда увидите что"
    )

    val who = Element(listOf(
            "разработчик",
            "тестировщик",
            "девопс",
            "девелопер"
    ))

    val theirSomething = Element(listOf(
            "свой scram",
            "свои пулреквесты",
            "свой пулреквест",
            "свой коммит мессадж",
            "свою невинность"
    ))

    val does = Element(listOf(
            "развивает",
            "улучшает",
            "запускает"
    ))

    val doesTheirWhat = Element(listOf(
            "[does] [what]"
    ))

    // кто-то делает что-то
    val someoneDoes = Element(listOf(
            "[who] [does]"
    ))

    init {
        doesTheirWhat.addSub("does", does)
        doesTheirWhat.addSub("what", theirSomething)

        someoneDoes.addSub("who", who)
        someoneDoes.addSub("does", doesTheirWhat)
    }

    fun generate() = someoneDoes.generate()
}