object Main extends App {
  var board = BoardGenerator(UlamSpiralGenerator.gen(99))
  UlamSpiralGenerator.printGrid(UlamSpiralGenerator.gen(55))
  KnightSimulator.sim(board)
}
