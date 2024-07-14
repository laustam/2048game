package game2048.logic

import engine.random.RandomGenerator

case class Board(board: Seq[Seq[Tile]],
                 randGen: RandomGenerator,
                 score: Int = 0,
                 isGameOver: Boolean = false,
                 isWon: Boolean = false) {

  def updateScore(inc: Int): Board = {
    copy(score = score + inc)
  }

  def step(dir: Point): Board = {
    def canStep: Boolean = {
      !(move(Left) == this &&
        move(Right) == this &&
        move(Up) == this &&
        move(Down) == this)
    }

    if (!canStep) return copy(isGameOver = true)

    {
      if (move(dir) == this)
        this
      else if (move(dir).goalReached)
        move(dir).copy(isWon = true)
      else
        move(dir).addNewTile()
    }
  }

  def move(dir: Point): Board = {
    var newBoard: Board = this

    // keeps track of cells that have been merged, so no double-merging occurs
    var mergedCells: Seq[Point] = Seq.empty

    // board is traversed in different order depending on the direction moved
    val rows: Range = dir match {
      case Down => board.indices.reverse
      case _ => board.indices
    }
    val cols: Range = dir match {
      case Right => board.head.indices.reverse
      case _ => board.head.indices
    }

    for (row <- rows; col <- cols) {
      if (at(col, row).get != Empty) {
        var currPt: Point = Point(col, row)

        def targetPt: Point = currPt + dir

        // move tile until can move no more
        while (newBoard.at(targetPt).contains(Empty)) {
          newBoard = newBoard
            .update(targetPt, at(col, row).get)
            .update(currPt, Tile())
          currPt = targetPt
        }

        // check if cells can be merged
        if (!mergedCells.contains(targetPt) && newBoard.at(targetPt).contains(newBoard.at(currPt).get)) {
          newBoard = newBoard
            .update(targetPt, Tile(at(col, row).get.i * 2))
            .update(currPt, Tile())
            .updateScore(at(col, row).get.i * 2)
          mergedCells = mergedCells :+ targetPt
        }
      }
    }
    newBoard
  }

  def at(pt: Point): Option[Tile] = {
    if (!outOfBounds(pt))
      Some(board(pt.y)(pt.x))
    else None
  }

  def at(x: Int, y: Int): Option[Tile] = {
    at(Point(x, y))
  }

  def update(pt: Point, newVal: Tile): Board = {
    copy(board = board.updated(pt.y, board(pt.y).updated(pt.x, newVal)))
  }

  def goalReached: Boolean = {
    for (row <- board.indices) {
      for (col <- board(row).indices) {
        if (at(col, row).get == Goal) {
          return true
        }
      }
    }
    false
  }

  def getEmptyPoints: Seq[Point] = {
    var resSeq: Seq[Point] = Seq.empty

    for (row <- board.indices; col <- board(row).indices) {
      if (board(row)(col).i == 0)
        resSeq = resSeq :+ Point(col, row)
    }
    resSeq
  }

  def addNewTile(): Board = {
    def getRandomPoint: Point = {
      val availPoints: Seq[Point] = getEmptyPoints
      val randIndex: Int = randGen.randomInt(availPoints.length)
      availPoints(randIndex)
    }

    def getNewTile: Tile = {
      val randomTileNr: Int = math.pow(2, randGen.randomInt(2) + 1).toInt
      Tile(randomTileNr)
    }

    update(getRandomPoint, getNewTile)
  }

  def outOfBounds(pt: Point): Boolean = {
    pt.x < 0 || pt.x >= board.length || pt.y < 0 || pt.y >= board.head.length
  }
}

object Board {
  def init(initialBoard: Seq[Seq[Tile]], randGen: RandomGenerator): Board = {
    Board(initialBoard, randGen).addNewTile().addNewTile()
  }
}