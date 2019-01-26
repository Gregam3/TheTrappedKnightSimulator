/**
  * This is not my code! Taken from here https://rosettacode.org/wiki/Ulam_spiral_(for_primes)#Scala
  * and slightly edited to show all numbers and not just primes.
  */

object Board {
  def apply(grid: Array[Array[Integer]]): Board = new Board(grid)
}

class Board(grid: Array[Array[Integer]]) {
  def apply = for (y <- grid.indices) yield
    for (x <- grid(y).indices) yield
      Square(
        safeGrid(y, x),
        safeGrid(y - 1, x),
        safeGrid(y + 1, x),
        safeGrid(y, x - 1),
        safeGrid(y, x + 1)
      )

  private def safeGrid(y: Int, x: Int) = {
    if (y > grid.length - 1 || y < 0) null
    else if (x > grid(y).length - 1 || x < 0) null
    else grid(x)(y)
  }
}
