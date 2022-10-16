package jt.projects.gbmaterialapp.util

import dagger.Component
import dagger.Module
import dagger.Provides
import jt.projects.gbmaterialapp.MainActivity
import javax.inject.Inject


/**В Dagger 2 есть несколько базовых компонентов, на которых будет строиться приложение. Чтобы лучше понять происходящее, коротенько пробежимся по их функциональным возможностям.
Singletones (синглтоны, или одиночки) — это все те классы, которые уже были тобой написаны и ждут своей интеграции: сетевые запросы, кеширование и так далее. «Одиночество» заключается в том, что Dagger 2 полностью контролирует их жизненный цикл и не позволяет приложению генерить таких объектов больше, чем необходимо.
Modules (модули) — набор классов, описывающих поведение синглтонов. Фреймворк позволяет гибко конфигурировать правила использования объектов, модули нужны именно для этого.
Component (компонент) — хранит в себе ссылки на все необходимые части приложения: синглтоны, модули, активити и другие. Фреймворк будет инжектировать зависимости на основе данных, полученных из компонента.
Класс Application — не совсем часть фреймворка, но важная составляющая. Сгенерированный код должен функционировать на протяжении жизни всего приложения, от самого старта и до конца. За жизненный цикл приложения в целом отвечает класс Application, его нужно будет расширить и вставить туда запрос на инициализацию фреймворка.
Аннотацией @Module мы сообщаем даггеру, что этот класс является модулем. А аннотация @Provides указывает, что метод является поставщиком объекта.
 **/
class Car @Inject constructor(private var engine: Engine){
    fun getType():String{
        return "${engine.rpm} : ${engine.fuel.fuelType}"
    }
}

class Engine @Inject constructor(var fuel: Fuel){
    val rpm = 320
}

class Fuel @Inject constructor() {
    val fuelType = "benzine"
}

//Dagger должен знать как создавать все объекты которые он должен внедрять.
//@Component
//interface CarsComponent {
//    fun getCar(): Car
//    fun getEngine(): Engine
//    fun getFuel(): Fuel
//    //В этом случае мы не просим компонент создать и вернуть нам конкретный объект.
//    // Вместо этого мы просим компонент проверить какие объекты нужны в MainActivity.
//    // Компонент создаст эти объекты и передаст их сразу в MainActivity. Такая процедура называется инджект.
//   // fun injectMainActivity(mainActivity: MainActivity)
//}

//class Cat(private val name: String){
//
//    override fun toString(): String {
//        return "$name"
//    }
//}
//
//
//@Module
//class MyModule{
//
//    @Provides
//    fun provideCat(): Cat = Cat("Barsik")
//}

//@Component(modules = [MyModule::class])
//interface MyComponent{
//
//    fun inject(mainActivity: MainActivity)
//}