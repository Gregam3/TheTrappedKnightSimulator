object UlamSpiralGenerator {
  private object Direction extends Enumeration {
    val RIGHT, UP, LEFT, DOWN, CENTER = Value
  }

  //    Code found in gen() was initially taken from here https://rosettacode.org/wiki/Ulam_spiral_(for_primes)#Scala
  //    and edited for to suit the purposes of this task

  def gen(n: Int) = {
    assert(n % 11 == 0, "grid size must be divisible by 11")
    //using Integer over Int to to be able to check nulls
    val grid = new Array[Array[Integer]](n).map(_ => new Array[Integer](n))

    import Direction._
    var dir = RIGHT
    var y = n / 2
    var x = y

    for (j <- 1 to n * n) {
      grid(y)(x) = j

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
    grid
  }

  def printGrid(grid: Array[Array[Integer]]) = println(grid.map(_.map(format(_)).mkString(",")).mkString("\n"))

  def format(n: Int) = {
    "%6d".format(n.toString.toInt)
  }
}
