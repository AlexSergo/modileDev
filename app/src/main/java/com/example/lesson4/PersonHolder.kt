package com.example.lesson4

import android.os.AsyncTask
import android.util.Log
import java.util.concurrent.TimeUnit

typealias PersonsListener = (person: Person?) -> Unit

object PersonHolder {
    var persons = mutableListOf<Person>()
    private var listeners = mutableSetOf<PersonsListener>()

    init {
        persons.add(Person(
            name = "Павел Дуров",
            sex = "мужчина",
            date = "1984-н.в.",
            info = "российский предприниматель, программист, долларовый миллиардер, " +
                    "один из создателей социальной сети «ВКонтакте»",
            image_path = "https://s0.rbk.ru/v6_top_pics/resized/590xH/media/img/0/39/756177114615390.jpg"))

        persons.add(Person(
            name = "Сатоши Накамото",
            sex = "неизвестно",
            date = "неизвестно",
            info = "псевдоним человека или группы людей, разработавших протокол криптовалюты биткойн " +
                    "и создавших первую версию программного обеспечения, в котором этот " +
                    "протокол был реализован",
            image_path = "https://hashtelegraph.com/wp-content/uploads/2017/12/ap499078584510.0-1024x683.jpg"))

        persons.add(Person(
            name = "Джеймс Хэтфилд",
            sex = "мужчина",
            date = "1963-н.в.",
            info = "американский рок-музыкант; вокалист и ритм-гитарист метал-группы Metallica. " +
                    "Мультиинструменталист.",
            image_path = "https://cdnn21.img.ria.ru/images/148971/13/1489711308_42:0:1313:1271_1920x0_80_0_0_ac555d87e66fa8a930f7d29303a0e95d.jpg"))

        persons.add(Person(
            name = "Аня Тейлор-Джой",
            sex = "женщина",
            date = "1996-н.в.",
            info = "американо-британо-аргентинская актриса и модель.",
            image_path = "https://www.film.ru/sites/default/files/people/6267155-1240162.jpg"))

        persons.add(Person(
            name = "Илон Маск",
            sex = "мужчина",
            date = "1971-н.в.",
            info = "Основатель, совладелец, генеральный директор и главный инженер компании SpaceX; " +
                    "генеральный директор    и главный идейный вдохновитель (Chief Product Architect) " +
                    "компании Tesla; также был членом совета директоров компании SolarCity",
            image_path = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Elon_Musk_Royal_Society.jpg/250px-Elon_Musk_Royal_Society.jpg"))

        persons.add(Person(
            name = "Екатерина II",
            sex = "женщина",
            date = "1729-1796",
            info = "императрица и Самодержица Всероссийская. Политик просвещённого абсолютизма. " +
                    "Дочь князя Ангальт-Цербстского, Екатерина взошла на престол в результате " +
                    "дворцового переворота против своего мужа — Петра III, вскоре погибшего при " +
                    "невыясненных обстоятельствах.",
            image_path = "https://krr.aero/upload/medialibrary/507/Екатерина%202%20портрет.jpg"))

        persons.add(Person(
            name = "Стивен Хокинг",
            sex = "мужчина",
            date = "1942-2018",
            info = "английский физик-теоретик, космолог и астрофизик, писатель, директор по научной " +
                    "работе Центра теоретической космологии Кембриджского университета.",
            image_path = "https://img.labirint.ru/images/upl/descripts/pic_1477575042.jpg"))

        persons.add(Person(
            name = "Коко Шанель",
            sex = "женщина",
            date = "1883-1971",
            info = "французский модельер, основательница модного дома Chanel. Оказала существенное " +
                    "влияние на европейскую моду XX века; единственный человек из мира моды, кого " +
                    "журнал «Тайм» внёс в список ста самых влиятельных людей XX века.",
            image_path = "https://cdn22.img.ria.ru/images/67913/83/679138374_0:128:800:578_600x0_80_0_0_7b8190428715e20e08fa88eed86f0361.jpg"))
    }

    fun addListener(listener: PersonsListener) {
        listeners.add(listener)
    }

    fun sendMessage() {
        Log.i("[APP]", "Sending message")
        for (listener in listeners)
            listener.invoke(persons.firstOrNull())
        if (persons.count() > 0)
            persons.removeAt(0)
    }

    fun removeListener(listener: PersonsListener) {
        listeners.remove(listener)
    }
}