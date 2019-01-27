import scala.collection.mutable

object KnightSimulator {
  def sim(board: Map[Int, mutable.Set[Int]]) = {
    var canMove = true
    var pos = 1
    var path = mutable.ArrayBuffer[Int]()

    while(canMove) {
      board(pos).foreach{board(_) -= pos}
      if(board(pos).isEmpty) canMove = false
      else {
        pos = board(pos).min
        path += pos
      }
    }

    println(path)
  }
}
