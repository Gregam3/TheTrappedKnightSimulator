object Main extends App {
  val board = BoardGenerator(UlamSpiralGenerator.gen(99))
  println("Sequence = " + KnightSimulator.sim(board).mkString("->"))
}
