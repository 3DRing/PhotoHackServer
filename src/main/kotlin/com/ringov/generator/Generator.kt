package com.ringov.generator

class Generator {

    companion object {

        private val lang = Element(listOf(
            "Java",
            "JS",
            "python",
            "с++",
            "kotlin",
            "swift"
        ))

        private val engTerm = Element(listOf(
            "code-review",
            "scrum",
            "agile"
        ))

        private val whatKindOf = Element(listOf(
            "",
            "простой",
            "известный",
            "фрилансер",
            "этот",
            "хитрый"
        ))

        private val who = Element(listOf(
            "[kind] разработчик",
            "[kind] тестировщик",
            "[kind] девопс",
            "[kind] девелопер",
            "[kind] менеджер",
            "[kind] дизайнер"
        ))
            .addSub("kind", whatKindOf)

        private val theirSomething = Element(listOf(
            "свой scram",
            "свои пулреквесты",
            "свой пулреквест",
            "свой коммит месседж",
            "свои таски"
        ))

        private val does = Element(listOf(
            "развивает",
            "улучшает",
            "запускает",
            "крутит"
        ))

        private val doesTheirWhat = Element(listOf(
            "[does] [what]"
        ))
            .addSub("does", does)
            .addSub("what", theirSomething)

        // кто-то делает что-то
        private val someoneDoes = Element(listOf(
            "[who] [does]",
            "[who] зарабатывает 50.000$ в месяц!",
            "[who] масштабирует свой СЕРВЕР",
            "[who] не собирал проект уже месяц"
        ))
            .addSub("who", who)
            .addSub("does", doesTheirWhat)


        private val exclamationOrNot = Element(listOf("!", "."))

        private val shock = Element(listOf(
            "шок",
            "вы не поверите",
            "ты не поверишь",
            "в такое сложно поверить",
            "невероятно, но",
            "внимание"
        ), true)

        private val shockSentence = Element("[shock][mark]")
            .addSub("shock", shock)
            .addSub("mark", exclamationOrNot)

        private val justNeedTo = Element(listOf(
            "нужно всего лишь",
            "нужно только",
            "нужно просто",
            "нужно взять за привычку",
            "надо перед сном",
            "нужно на ночь"
        ))

        private val needToWhat = Element(listOf(
            "подключить ЭТОТ плагин...",
            "написать в коммит месседж...",
            "убрать из рациона...",
            "намазать жесткий диск обычной...",
            "засунуть флешку в...",
            "переносить задачу в...",
            "скомпилировать перед сном этот...",
            "в облаке ЭТО...",
            "обучить свою нейронку на...",
            "носить...",
            "поставить свой ноутбук на...",
            "перестать писать на [lang]",
            "перестать практиковать [eng_term]",
            "прочитать на ночь манул по [lang]",
            "каждое утро делать [eng_term]"
        ))
            .addSub("lang", lang)
            .addSub("eng_term", engTerm)

        private val company = Element(listOf(
            "google", "yandex", "mail.ru", "facebook", "avito", "telegram"
        ))

        private val setup = Element(listOf(
            "устроиться в [company] не сложно!",
            "не билдится проект?",
            "тебе одиноко? Никто никто не понимает? Есть выход!",
            "останови издевательства тимлида",
            "британские ученые узнали, откуда берутся дети у программистов.",
            "если ноют суставы,",
            "пахнет изо рта? не проблема!",
            "найден легкий способ избавиться от [eng_term]",
            "вырваться из безденежья",
            "деньги сами пойдут в карман!",
            "вам положены дополнительные две недели отпуска!",
            "роскошная яхта всего за 1 биткоин!",
            "развивать ум и логику легко",
            "разбогатеть легко!",
            "масштабируй свой СЕРВЕР без регистрации и смс",
            "твоим коллегам уже доступен бесплатный MacBook Pro",
            "чтобы написать искусственный интеллект на [lang]",
            "живот уйдет сам по себе!",
            "можно узнать будущее уже сейчас"
        ))
            .addSub("company", company)
            .addSub("eng_term", engTerm)
            .addSub("lang", lang)

        private val bait = Element(listOf(
            "для этого [just_need] [what]",
            "сделай ЭТО...",
            "узнать КАК...",
            "это не для слабонервных..."
        ))
            .addSub("just_need", justNeedTo)
            .addSub("what", needToWhat)

        private val templates = Element(listOf(
            "[shock] [what], [how]",
            "[shock] [setup], [how]"
        ))
            .addSub("shock", shockSentence)
            .addSub("what", someoneDoes)
            .addSub("setup", setup)
            .addSub("how", bait)
            .addSub("lang", lang)

        fun generate() = templates.generate()
    }
}
