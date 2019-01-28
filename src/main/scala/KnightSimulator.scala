import scala.collection.mutable

object KnightSimulator {
  def sim(board: Map[Int, mutable.Set[Integer]]) = {
    var pos = 1
    var path = mutable.ArrayBuffer[Int]()

    while(pos != 2084) {
      board(pos).foreach{board(_) -= pos}
      path += pos
      if(board(pos).nonEmpty) pos = board(pos).min
    }

    path += 2084
  }
}