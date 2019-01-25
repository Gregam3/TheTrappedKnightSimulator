class Square(var center: Int,var top: Int, var bottom: Int, var left: Int, var right: Int) {
  override def toString = {
    top + "\n" +
    left + " " + center + " " + right +
    bottom
  }
}
