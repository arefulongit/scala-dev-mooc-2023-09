package howework.hw01

import scala.annotation.tailrec

sealed trait MyList[+T] {

  def cons[T1 >: T](el: T1): MyList[T1] = {
    MyCons(el, this)
  }

  def filter[B](filterFunc: T => Boolean): MyList[T] = {
    @tailrec
    def f(myList: MyList[T], retList: MyList[T]): MyList[T] = {
      myList match {
        case MyCons(head, tail) =>
          if(filterFunc(head)){
            f(tail, retList.cons(head))
          } else {
            f(tail, retList)
          }
        case MyNil => retList
      }
    }
    f(this, MyNil)
  }


  def map[B](mapFunc: T => B): MyList[B] = {
    @tailrec
    def f(myList: MyList[T], retList: MyList[B]): MyList[B] = {
      myList match {
        case MyCons(head, tail) =>
          f(tail, retList.cons(mapFunc(head)))
        case MyNil => retList
      }
    }

    f(this, MyNil)
  }

  def mkString(delimiter: String): String = {
    @tailrec
    def f(myList: MyList[T], delimiter: String, rString: String): String = {
      myList match {
        case MyNil =>
          rString
        case MyCons(x, MyNil) =>
          rString + x.toString
        case MyCons(head, tail) =>
          f(tail, delimiter, rString + head.toString + delimiter)
      }
    }

    f(this, delimiter, "")
  }

  def reverse: MyList[T] = this.map(x => x)

}

case class MyCons[T](head: T, tail: MyList[T]) extends MyList[T]

case object MyNil extends MyList[Nothing]


object MyList {
  def apply[T](listOfParams: T*): MyList[T] = {

    @tailrec
    def f(myList: MyList[T], remainderOfLength: Int, listOfParams: Seq[T]): MyList[T] = {
      if (remainderOfLength > 0) {
        f(MyCons(listOfParams(remainderOfLength - 1), myList), remainderOfLength - 1, listOfParams)
      } else {
        myList
      }
    }

    f(MyNil, listOfParams.size, listOfParams)
  }
}
