package com.example.lesson4

typealias PersonsListener = (persons: List<Person>) -> Unit

object PersonHolder {
    private var persons = mutableListOf<Person>()
    private var listeners = mutableSetOf<PersonsListener>()

    init {
        persons.add(Person("Павел Дуров", "мужчина", "1984-н.в.",
            "российский предприниматель, программист, долларовый миллиардер, " +
                    "один из создателей социальной сети «ВКонтакте»",
            "https://s0.rbk.ru/v6_top_pics/resized/590xH/media/img/0/39/756177114615390.jpg"))

        persons.add(Person("Сатоши Накамото", "неизвестно", "неизвестно",
            "псевдоним человека или группы людей, разработавших протокол криптовалюты биткойн " +
                    "и создавших первую версию программного обеспечения, в котором этот " +
                    "протокол был реализован",
            "https://hashtelegraph.com/wp-content/uploads/2017/12/ap499078584510.0-1024x683.jpg"))

        persons.add(Person("Джеймс Хэтфилд", "мужчина", "1963-н.в.",
            "американский рок-музыкант; вокалист и ритм-гитарист метал-группы Metallica. " +
                    "Мультиинструменталист.",
            "https://cdnn21.img.ria.ru/images/148971/13/1489711308_42:0:1313:1271_1920x0_80_0_0_ac555d87e66fa8a930f7d29303a0e95d.jpg"))

        persons.add(Person("Аня Тейлор-Джой", "женщина", "1996-н.в.",
            "американо-британо-аргентинская актриса и модель.",
            "https://www.film.ru/sites/default/files/people/6267155-1240162.jpg"))

        persons.add(Person("Илон Маск", "мужчина", "1971-н.в.",
            "Основатель, совладелец, генеральный директор и главный инженер компании SpaceX; " +
                    "генеральный директор    и главный идейный вдохновитель (Chief Product Architect) " +
                    "компании Tesla; также был членом совета директоров компании SolarCity",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Elon_Musk_Royal_Society.jpg/250px-Elon_Musk_Royal_Society.jpg"))

        persons.add(Person("Екатерина II", "женщина", "1729-1796",
            "императрица и Самодержица Всероссийская. Политик просвещённого абсолютизма. " +
                    "Дочь князя Ангальт-Цербстского, Екатерина взошла на престол в результате " +
                    "дворцового переворота против своего мужа — Петра III, вскоре погибшего при " +
                    "невыясненных обстоятельствах.",
            "https://krr.aero/upload/medialibrary/507/Екатерина%202%20портрет.jpg"))

        persons.add(Person("Стивен Хокинг", "мужчина", "1942-2018",
            "английский физик-теоретик, космолог и астрофизик, писатель, директор по научной " +
                    "работе Центра теоретической космологии Кембриджского университета.",
            "https://img.labirint.ru/images/upl/descripts/pic_1477575042.jpg"))

        persons.add(Person("Коко Шанель", "женщина", "1883-1971",
            "французский модельер, основательница модного дома Chanel. Оказала существенное " +
                    "влияние на европейскую моду XX века; единственный человек из мира моды, кого " +
                    "журнал «Тайм» внёс в список ста самых влиятельных людей XX века.",
            "https://cdn22.img.ria.ru/images/67913/83/679138374_0:128:800:578_600x0_80_0_0_7b8190428715e20e08fa88eed86f0361.jpg"))
    }

    fun addListener(listener: PersonsListener){
        listeners.add(listener)
        listener.invoke(persons)
    }

    fun removeListener(listener: PersonsListener){
        listeners.remove(listener)
    }
}