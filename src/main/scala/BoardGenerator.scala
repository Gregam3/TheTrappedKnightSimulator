import scala.collection.mutable

object BoardGenerator {
  def apply(grid: Array[Array[Integer]]) = new BoardGenerator(grid).gen
}

class BoardGenerator(grid: Array[Array[Integer]]) {
  private[this] val one = Set(1, -1)
  private[this] val two = Set(2,-2)

  def gen = {
    var board = Map[Int, mutable.Set[Integer]]()

    for (y <- grid.indices) {
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
        ).filter(_ != null)).toMap
    }

    board
  }

  private def safeGrid(y: Int, x: Int) = {
    if (y > grid.length - 1 || y < 0 ||
      x > grid(y).length - 1 || x < 0) null
    else grid(y)(x)
  }
}
