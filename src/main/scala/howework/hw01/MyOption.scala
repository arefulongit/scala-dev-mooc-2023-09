package howework.hw01

sealed trait MyOption[+IT] {
  //  def isEmpty
  //  def get
  //  def map
  //  def flatMap
  def isEmpty: Boolean = {
    this match {
      case MySome(_) => false
      case MyNone => true
    }
  }

  def get:IT = {
    this match {
      case MySome(value) => value
      case MyNone =>
        throw new NoSuchElementException("value not found")
    }
  }

  def map[RT](f: (IT)=>RT):MyOption[RT] = {
    this match {
      case MySome(value) =>
        MySome(f(value))
      case MyNone =>
        MyNone
    }
  }

  def flatMap[RT](f: IT=>MyOption[RT]):MyOption[RT] = {
    this match {
      case MySome(value)=>
        f(value)
      case MyNone =>
        MyNone
    }
  }

  def printIfAny:Unit = {
    this match {
      case MySome(value)=>
        println(value)
      case MyNone =>
        ()
    }
  }

  def filter(f: IT=>Boolean):MyOption[IT] = {
    this match {
      case MySome(value) if f(value) == true =>
        MySome(value)
      case _=>
        MyNone
    }
  }

}

case class MySome[T](value: T) extends MyOption[T]

case object MyNone extends MyOption[Nothing]

object MyOption{
  def apply[IT](v: IT):MyOption[IT] = {
    MySome(v)
  }

  def zip[IT1, IT2](v1: MyOption[IT1], v2: MyOption[IT2]): MyOption[(IT1, IT2)] = {
    (v1, v2) match {
      case (MySome(val1), MySome(val2)) =>
        MySome((val1,val2))
      case (_,_) => MyNone
    }
  }
}
