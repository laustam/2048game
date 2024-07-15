package game2048.game

import java.awt.event
import java.awt.event.KeyEvent._
import engine.GameBase
import engine.graphics.{Color, Point, Rectangle}
import game2048.logic.{Tile, Dimensions, GameLogic, Point => GridPoint}
import processing.core.{PApplet, PConstants}
import processing.event.KeyEvent
import Game._

class Game extends GameBase {

  var gameLogic: GameLogic = GameLogic()
  val gridDims: Dimensions = gameLogic.gridDims
  val gridWidthInPixels: Int = (WidthCellInPixels * gridDims.width).ceil.toInt
  val gridHeightInPixels: Int = (HeightCellInPixels * gridDims.height).ceil.toInt
  val headerHeightInPixels: Int = 60
  val widthInPixels: Int = gridWidthInPixels
  val heightInPixels: Int = gridHeightInPixels + headerHeightInPixels
  val headerArea: Rectangle = Rectangle(Point(0, 0), widthInPixels.toFloat, headerHeightInPixels.toFloat)
  val gridArea: Rectangle = Rectangle(Point(0, headerHeightInPixels.toFloat), widthInPixels.toFloat, gridHeightInPixels.toFloat)

  override def draw(): Unit = {
    drawHeader()
    drawGrid()
    if (gameLogic.isGameOver) drawEndScreen("GAME OVER!", Color.Black)
    else if (gameLogic.isWon) drawEndScreen("YOU WIN!", Color.Brown)
  }

  def drawHeader(): Unit = {
    setFillColor(Color.COLOR_EMPTY)
    drawRectangle(headerArea)
    drawTitle()
    drawScore()
  }

  def drawTitle(): Unit = {
    setFillColor(Color.Brown)
    val text: String = "2048"
    val fontSize: Float = headerArea.width / 8
    val position: Point = headerArea.center + Point(-(widthInPixels.toFloat / 3.5).toFloat, (headerArea.height / 2))
    drawTextCentered(text, fontSize, position)
  }

  def drawScore(): Unit = {
    setFillColor(Color.Grey)
    val text: String = "Score:\n" + gameLogic.getScore.toString
    val fontSize: Float = headerArea.width / 16
    val position: Point = headerArea.center + Point(widthInPixels.toFloat / 3, (headerArea.height / 4.5).toFloat)
    drawTextCentered(text, fontSize, position)
  }

  def drawEndScreen(text: String, color: Color): Unit = {
    def drawNewGame(): Unit = {
      setFillColor(Color.Grey)
      val text: String = "New Game"
      val fontSize: Float = gridArea.width / 16
      val position: Point = gridArea.center + Point(0, 20)
      drawTextCentered(text, fontSize, position)
    }

    setBackground(Color.COLOR_EMPTY)
    setFillColor(color)
    val fontSize: Float = gridArea.center.x / 4
    drawTextCentered(text, fontSize, gridArea.center)
    drawNewGame()
  }

  def drawGrid(): Unit = {
    val widthPerCell = gridArea.width / gridDims.width
    val heightPerCell = gridArea.height / gridDims.height

    for (p <- gridDims.allPointsInside) {
      drawCell(getCell(p), gameLogic.getCellType(p))
    }

    def getCell(p: GridPoint): Rectangle = {
      val leftUp = Point(gridArea.left + p.x * widthPerCell,
        gridArea.top + p.y * heightPerCell)
      Rectangle(leftUp, widthPerCell, heightPerCell)
    }

    def drawCell(area: Rectangle, tile: Tile): Unit = {
      val color = tetrisBlockToColor(tile)
      setFillColor(color)
      drawRectangle(area)
      if (tile.i != 0)
        drawTileNumber(area, tile)
    }

    def drawTileNumber(area: Rectangle, tile: Tile): Unit = {
      val num: String = tile.i.toString
      setFillColor(Color.Grey)
      drawTextCentered(num, area.width / 3, area.center + Point(0, area.height / 4))
    }
  }

  /** Method that calls handlers for different key press events.
    * You may add extra functionality for other keys here.
    * See [[event.KeyEvent]] for all defined keycodes.
    *
    * @param event The key press event to handle
    */
  override def keyPressed(event: KeyEvent): Unit = {
    if(gameLogic.isGameOver | gameLogic.isWon) {
      if (event.getKeyCode == VK_R) gameLogic = GameLogic()
      return
    }

    event.getKeyCode match {
      case VK_UP => gameLogic.moveUp()
      case VK_DOWN => gameLogic.moveDown()
      case VK_LEFT => gameLogic.moveLeft()
      case VK_RIGHT => gameLogic.moveRight()
      case _ => ()
    }
  }

  override def settings(): Unit = {
    pixelDensity(displayDensity())
    // If line below gives errors try size(widthInPixels, heightInPixels, PConstants.P2D)
    size(widthInPixels, heightInPixels)
  }

  override def setup(): Unit = {
    // Fonts are loaded lazily, so when we call text()
    // for the first time, there is significant lag.
    // This prevents it from happening during gameplay.
    text("", 0, 0)
  }

  def tetrisBlockToColor(tile: Tile): Color = {
    tile.i match {
      case 2 => Color.COLOR_2
      case 4 => Color.COLOR_4
      case 8 => Color.COLOR_8
      case 16 => Color.COLOR_16
      case 32 => Color.COLOR_32
      case 64 => Color.COLOR_64
      case 128 => Color.COLOR_128
      case 256 => Color.COLOR_256
      case 512 => Color.COLOR_512
      case 1024 => Color.COLOR_1024
      case 2048 => Color.COLOR_2048
      case _ => Color.COLOR_EMPTY
    }
  }
}

object Game {
  val WidthCellInPixels: Double = 15 * GameLogic.DrawSizeFactor
  val HeightCellInPixels: Double = WidthCellInPixels

  def main(args: Array[String]): Unit = {
    PApplet.main("game2048.game.Game")
  }
}