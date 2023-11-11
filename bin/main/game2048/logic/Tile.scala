package game2048.logic

case class Tile(i: Int = 0)

object Empty extends Tile

object Goal extends Tile(2048)