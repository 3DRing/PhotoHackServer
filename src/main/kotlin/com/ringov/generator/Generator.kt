package com.ringov.generator

class Generator {

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
            .addSub("does", does)
            .addSub("what", theirSomething)

    // кто-то делает что-то
    val someoneDoes = Element(listOf(
            "[who] [does]"
    ))
            .addSub("who", who)
            .addSub("does", doesTheirWhat)


    val exclamationOrNot = Element(listOf("!", "."))

    val shock = Element(listOf(
            "шок",
            "вы не поверите",
            "ты не поверишь",
            "в такое сложно поверить"
    ), true)
    val shockSentance = Element("[shock][mark]")
            .addSub("shock", shock)
            .addSub("mark", exclamationOrNot)

    val justNeedTo = Element(listOf(
            "нужно всего лишь",
            "нужно только"
    ))

    val needToWhat = Element(listOf(
            "знать",
            "использовать"
    ))

    val needToWhatExtra = Element(listOf(
            "",
            "это",
            "этот способ",
            ""
    ), true)

    val bait = Element(listOf(
            "для этого [just_need] [what] [extra]"
    ))
            .addSub("just_need", justNeedTo)
            .addSub("what", needToWhat)
            .addSub("extra", needToWhatExtra)

    val shockWhatHow = Element(listOf(
            "[shock] [what], [how]"
    ))
            .addSub("shock", shockSentance)
            .addSub("what", someoneDoes)
            .addSub("how", bait)

    fun generate() = shockWhatHow.generate()
}