package game2048.logic

import engine.random.{RandomGenerator, ScalaRandomGen}
import GameLogic._

class GameLogic(val randGen: RandomGenerator,
                val gridDims: Dimensions,
                val initialBoard: Seq[Seq[Tile]]) {

  var board: Board = Board.init(initialBoard, randGen)

  println("Initial board: ")
  TestShit.printBoard(board)

  def this(random: RandomGenerator, gridDims: Dimensions) =
    this(random, gridDims, makeEmptyBoard(gridDims))

  def this() =
    this(new ScalaRandomGen(), DefaultDims, makeEmptyBoard(DefaultDims))

  def moveLeft(): Unit = {
    board = board.step(Left)
    println("Move left: ")
    TestShit.printBoard(board)
  }

  def moveRight(): Unit = {
    board = board.step(Right)

    println("Move right: ")
    TestShit.printBoard(board)
  }

  def moveDown(): Unit = {
    board = board.step(Down)

    println("Move down: ")
    TestShit.printBoard(board)
  }

  def moveUp(): Unit = {
    board = board.step(Up)

    println("Move up: ")
    TestShit.printBoard(board)
  }

  def isGameOver: Boolean = board.isFull

  def hasWon: Boolean = board.isWon

  def getScore: Int = board.score

  def getCellType(p: Point): Tile = {
    board.at(p).get
  }
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

  def apply() = new GameLogic(new ScalaRandomGen(),
    DefaultDims,
    makeEmptyBoard(DefaultDims))
}