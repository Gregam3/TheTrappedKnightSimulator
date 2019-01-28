import scala.collection.mutable

object BoardGenerator {
  def apply(grid: Array[Array[Integer]]) = new BoardGenerator(grid).gen
}

class BoardGenerator(grid: Array[Array[Integer]]) {
  def gen = {
    var board = Map[Int, mutable.Set[Integer]]()

    for (y <- grid.indices) {
      board ++= grid(y).indices.map(x =>
        grid(y)(x).toInt ->
          (movementPermutationsAsSet(y, x, false) ++ movementPermutationsAsSet(y, x, true))
      ).filter(_ != null)
    }

    board
  }

  private def movementPermutationsAsSet(y: Integer, x: Integer, invert: Boolean) = {
    mutable.Set(-2, 2).flatMap(a => Set(-1, 1).map(b => if (invert) safeGrid(y + b, x + a) else safeGrid(y + a, x + b)))
  }

  private def safeGrid(y: Int, x: Int) = {
    if (y > grid.length - 1 || y < 0 ||
      x > grid(y).length - 1 || x < 0) null
    else grid(y)(x)
  }
}