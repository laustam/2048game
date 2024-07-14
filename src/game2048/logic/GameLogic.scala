package game2048.logic

import engine.random.{RandomGenerator, ScalaRandomGen}
import GameLogic._

class GameLogic(val randGen: RandomGenerator,
                val gridDims: Dimensions,
                val initialBoard: Seq[Seq[Tile]]) {

  var board: Board = Board.init(initialBoard, randGen)

  def this(random: RandomGenerator, gridDims: Dimensions) =
    this(random, gridDims, makeEmptyBoard(gridDims))

  def this() =
    this(new ScalaRandomGen(), DefaultDims, makeEmptyBoard(DefaultDims))

  def moveLeft(): Unit = {
    board = board.step(Left)
  }

  def moveRight(): Unit = {
    board = board.step(Right)
  }

  def moveDown(): Unit = {
    board = board.step(Down)
  }

  def moveUp(): Unit = {
    board = board.step(Up)
  }

  def isGameOver: Boolean = board.isGameOver

  def isWon: Boolean = board.isWon

  def getScore: Int = board.score

  def getCellType(p: Point): Tile = board.at(p).get
}

/* This object defines some boards for testing.
To test these, change initialBoard passed to GameLogic in apply()
TODO: figure out how to set up a proper testing environment */
object Testing {
  val gameOverBoard: Seq[Seq[Tile]] =
    List(List(Tile(8), Tile(4), Tile(8), Tile(0)),
      List(Tile(16), Tile(8), Tile(0), Tile(8)),
      List(Tile(2), Tile(4), Tile(8), Tile(16)),
      List(Tile(16), Tile(8), Tile(4), Tile(2)))

  val winBoard: Seq[Seq[Tile]] =
    List(List(Tile(2048), Tile(16), Tile(0), Tile(0)),
      List(Tile(16), Tile(8), Tile(16), Tile(8)),
      List(Tile(2), Tile(4), Tile(8), Tile(16)),
      List(Tile(16), Tile(8), Tile(4), Tile(2)))
}

object GameLogic {
  val DrawSizeFactor: Int = 5

  def makeEmptyBoard(gridDims: Dimensions): Seq[Seq[Tile]] = {
    val emptyLine = Seq.fill(gridDims.width)(Tile())
    Seq.fill(gridDims.height)(emptyLine)
  }

  val DefaultWidth: Int = 4
  val DefaultHeight: Int = 4
  val DefaultDims: Dimensions = Dimensions(width = DefaultWidth, height = DefaultHeight)

  def apply() = new GameLogic(
    randGen = new ScalaRandomGen(),
    gridDims = DefaultDims,
    initialBoard = makeEmptyBoard(DefaultDims))
}
