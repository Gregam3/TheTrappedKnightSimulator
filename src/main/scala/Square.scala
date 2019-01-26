object Square {
  def apply(center: Integer, top: Integer, bottom: Integer, left: Integer, right: Integer) = new Square(center, top, bottom, left, right)
}

//Integer used over int for nullable type
class Square(var center: Integer, var top: Integer, var bottom: Integer, var left: Integer, var right: Integer) {
  override def toString = {
    top + "\n" +
      left + " " + center + " " + right +
      bottom
  }
}
