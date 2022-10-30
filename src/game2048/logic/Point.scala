package game2048.logic

// you can alter this file!

case class Point(x: Int, y: Int) {
  def +(rhs: Point): Point = {
    Point(x + rhs.x, y + rhs.y)
  }
}

object Left extends Point(-1, 0)

object Right extends Point(1, 0)

object Up extends Point(0, -1)

object Down extends Point(0, 1)
