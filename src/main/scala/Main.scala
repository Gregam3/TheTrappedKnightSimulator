object Main extends App {
  var board = BoardGenerator(UlamSpiralGenerator.gen(99))
  var knightSequence = KnightSimulator.sim(board)
  println("Sequence = " + KnightSimulator.sim(board).mkString("->"))
}
