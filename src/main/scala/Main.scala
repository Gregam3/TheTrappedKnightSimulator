/**
  * This code was taken from here https://rosettacode.org/wiki/Ulam_spiral_(for_primes)#Scala
  * and edited for simplicity and to show all numbers and not just primes.
  */

object Main extends App {
  generate(55)

  private object Direction extends Enumeration { val RIGHT, UP, LEFT, DOWN = Value }

  private def generate(n: Int) {
    val grid = new Array[Array[String]](n).map {_ => new Array[String](n) }

    import Direction._
    var dir = RIGHT
    var y = n / 2
    var x = if (n % 2 == 0) y - 1 else y // shift left for even n'grid
    for (j <- 1 to n * n) {
      grid(y)(x) = "%4d".format(j)

      dir match {
        case RIGHT => if (x <= n - 1 && grid(y - 1)(x) == null && j > 1) dir = UP
        case UP => if (grid(y)(x - 1) == null) dir = LEFT
        case LEFT => if (x == 0 || grid(y + 1)(x) == null) dir = DOWN
        case DOWN => if (grid(y)(x + 1) == null) dir = RIGHT
      }

      dir match {
        case RIGHT => x += 1
        case UP => y -= 1
        case LEFT => x -= 1
        case DOWN => y += 1
      }
    }
    println(grid.map(_.mkString(",")).reduceLeft(_ + "\n" + _) + "\n")
  }
}
