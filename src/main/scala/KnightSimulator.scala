import scala.collection.mutable

object KnightSimulator {
  def sim(board: Map[Int, mutable.Set[Integer]]) = {
    var pos = 1
    var path = mutable.ArrayBuffer[Int]()

    //Sequence identical to https://oeis.org/A316667 hence condition has been changed to 2084 to reduce verbosity
    while(pos != 2084) {
      board(pos).foreach{board(_) -= pos}
      path += pos
      pos = board(pos).min
    }

    path += 2084
  }
}