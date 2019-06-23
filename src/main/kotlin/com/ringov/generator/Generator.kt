package com.ringov.generator

class Generator {

    companion object {
        private val engTerm = Element(listOf(
                "code-review",
                "scrum"
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
                "[kind] девелопер"
        )).addSub("kind", whatKindOf)

        private val theirSomething = Element(listOf(
                "свой scram",
                "свои пулреквесты",
                "свой пулреквест",
                "свой коммит мессадж"
        ))

        private val does = Element(listOf(
                "развивает",
                "улучшает",
                "запускает"
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
                "в такое сложно поверить"
        ), true)
        private val shockSentence = Element("[shock][mark]")
                .addSub("shock", shock)
                .addSub("mark", exclamationOrNot)

        private val justNeedTo = Element(listOf(
                "нужно всего лишь",
                "нужно только",
                "нужно просто",
                "нужно взять за привычку"
        ))

        private val needToWhat = Element(listOf(
                "подключить ЭТОТ плагин...",
                "написать в коммит месседж...",
                "убрать из рациона...",
                "намазать жесткий диск обычной...",
                "засунуть флешку в...",
                "переносить задачу в...",
                "скомпилировать перед сном этот...",
                "в обалаке ЭТО...",
                "обучить свою нейронку на...",
                "носить...",
                "поставить свой ноутбук на..."
        ))

        private val company = Element(listOf(
                "гугл", "яндекс", "google", "yandex", "mail.ru", "facebook"
        ))

        private val setup = Element(listOf(
                "Устроиться в [company] не сложно!",

                "Не билдится проект?",
                "Тебе одиноко? Никто никто не понимает? Есть выход!",
                "Чтобы перестал мучать гемморой,",
                "Британские ученые узнали, откуда берутся дети у программистов.",
                "Если ноют суставы,",
                "Висячий живот высохнет сам,",
                "Пахнет изо рта? Не проблема!",
                "Найден легкий способ избавиться от [eng_term]",
                "Чтобы вырваться из безденежья,",
                "Деньги сами пойдут в карман!",
                "Вам положены дополнительные две недели отпуска!",
                "Роскошная яхта всего за 1 биткоин!",
                "Развивать ум и логику легко -",
                "Разбогатеть легко!",
                "Масштабируй свой СЕРВЕР без регистрации и смс,",
                "Индусам уже доступен бесплатный интернет, для этого"
        ))
                .addSub("company", company)
                .addSub("eng_term", engTerm)

        private val lang = Element(listOf(
                "Java",
                "JS",
                "питон",
                "с++"
        ))

        private val bait = Element(listOf(
                "для этого [just_need] [what]",
                "сделайте ЭТО...",
                "Узнать КАК..."
        ))
                .addSub("just_need", justNeedTo)
                .addSub("what", needToWhat)

        private val templates = Element(listOf(
                "[shock] [what], [how]",
                "[shock] [setup], [how]",
                "Хватит писать на [lang], переходите на..."
        ))
                .addSub("shock", shockSentence)
                .addSub("what", someoneDoes)
                .addSub("setup", setup)
                .addSub("how", bait)
                .addSub("lang", lang)

        fun generate() = templates.generate()
    }
}