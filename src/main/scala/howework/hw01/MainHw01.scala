package howework.hw01

case object MainHw01 {

  def main(args:Array[String]):Unit = {
    println("Hello HW 03")

    val optSome:MyOption[String] = MySome("is my Option")
    val optNone = MyNone

    optSome match {
      case MySome(value) => println(s"opt value: ${value}")
      case MyNone => println(s"opt is None")
    }

    /**
     *
     * Реализовать метод printIfAny, который будет печатать значение, если оно есть
     */
    optSome.printIfAny
    optNone.printIfAny

    /**
     *
     * Реализовать метод zip, который будет создавать Option от пары значений из 2-х Option
     */
    MyOption.zip(optSome,optSome).printIfAny
    MyOption.zip(optSome,optNone).printIfAny

    /**
     *
     * Реализовать метод filter, который будет возвращать не пустой Option
     * в случае если исходный не пуст и предикат от значения = true
     */
    optSome.filter(str => str.length > 0).printIfAny
    optSome.filter(str => str.length < 0).printIfAny

    /**
     * Метод cons, добавляет элемент в голову списка, для этого метода можно воспользоваться названием `::`
     *
     */
    val list1:MyList[Int] = MyList(1).cons(2).cons(3).cons(4).cons(5)
    println(s"list1: ${list1}")

    /**
     * Метод mkString возвращает строковое представление списка, с учетом переданного разделителя
     *
     */
    println(s"list1.mkString: ${list1.mkString(",")}")

    /**
     * Конструктор, позволяющий создать список из N - го числа аргументов
     * Для этого можно воспользоваться *
     *
     * Например вот этот метод принимает некую последовательность аргументов с типом Int и выводит их на печать
     * def printArgs(args: Int*) = args.foreach(println(_))
     */
    val list2 = MyList(1,2,3,4,5)
    println(s"list2: ${list2}")
    println(s"list2.mkString: ${list2.mkString(",")}")

    /**
     *
     * Реализовать метод reverse который позволит заменить порядок элементов в списке на противоположный
     */
    val list3 = list2.reverse
    println(s"list3: ${list3}")
    println(s"list3.mkString: ${list3.mkString(",")}")

    /**
     *
     * Реализовать метод map для списка который будет применять некую ф-цию к элементам данного списка
     */
    val list4 = list2.map{ x => s"x$x"}
    println(s"list4: ${list4}")
    println(s"list4.mkString: ${list4.mkString(",")}")

    /**
     *
     * Реализовать метод filter для списка который будет фильтровать список по некому условию
     */
    val list5 = list2.filter(x => x > 3)
    println(s"list5: ${list5}")
    println(s"list5.mkString: ${list5.mkString(",")}")

    /**
     *
     * Написать функцию incList котрая будет принимать список Int и возвращать список,
     * где каждый элемент будет увеличен на 1
     */
    val list6 = incList(list2)
    println(s"list6: ${list6}")
    println(s"list6.mkString: ${list6.mkString(",")}")

    /**
     *
     * Написать функцию shoutString котрая будет принимать список String и возвращать список,
     * где к каждому элементу будет добавлен префикс в виде '!'
     */
    val list7 = shoutString(list4)
    println(s"list7: ${list7}")
    println(s"list7.mkString: ${list7.mkString(",")}")


  }

  def incList(myList1: MyList[Int]):MyList[Int] = {
    myList1.map(x=>x+1)
  }

  def shoutString(myList1: MyList[String]):MyList[String] = {
    myList1.map(x => s"!${x}")
  }

}
