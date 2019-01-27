import scala.collection.mutable

object BoardGenerator {
  def apply(grid: Array[Array[Integer]]) = new BoardGenerator(grid).gen
}

class BoardGenerator(grid: Array[Array[Integer]]) {
  def gen = {
    var board = Map[Int, mutable.Set[Int]]()

    for (y <- grid.indices)
      board ++= grid(y).indices.map(x =>
        grid(y)(x).toInt -> mutable.Set(
          safeGrid(y + 2, x + 1),
          safeGrid(y + 2, x - 1),
          safeGrid(y + 1, x + 2),
          safeGrid(y + 1, x - 2),
          safeGrid(y - 1, x + 2),
          safeGrid(y - 1, x - 2),
          safeGrid(y - 2, x + 1),
          safeGrid(y - 2, x - 1)
          //Converting back to Int for simplicity now items are non-nullable
        ).filter(_ != null)).toMap.map { case (k, v) => (k.toInt, v.map(_.toInt)) }

    board
  }

  private def safeGrid(y: Int, x: Int) = {
    if (y > grid.length - 1 || y < 0) null
    else if (x > grid(y).length - 1 || x < 0) null
    else grid(x)(y)
  }
}
