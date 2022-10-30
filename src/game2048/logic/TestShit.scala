package game2048.logic

object TestShit {
  def testCellTypes(p: Point, gridDims: Dimensions): Tile = {

    Tile(
      if (p == Point(0, 1)) 2
      else if (p == Point(0, 2)) 4
      else if (p == Point(0, 3)) 8
      else if (p == Point(0, 4)) 16
      else if (p == Point(0, 5)) 32
      else if (p == Point(1, 1)) 64
      else if (p == Point(1, 2)) 128
      else if (p == Point(1, 3)) 256
      else if (p == Point(1, 4)) 512
      else if (p == Point(1, 5)) 1024
      else if (p == Point(2, 0)) 2048
      else 0
    )
  }

  def printBoard(board: Board): Unit = {
    println("----------")
    for (row <- board.board.indices) {
      for (col <- board.board(row).indices)
        print(board.at(col, row).get.i.toString + "\t")
      println()
    }
    println("----------")
  }

  def testMergingRight(emptyboard : Board) : Board = {
    emptyboard.update(Point(1,1), Tile(4)).update(Point(2,1), Tile(2)).update(Point(3,1), Tile(2))
  }

  def testCantMoveLeft(emptyboard: Board): Board = {
    emptyboard.update(Point(0, 1), Tile(4)).update(Point(1, 1), Tile(2))
  }

  def testWinning(board: Board): Board = {
    board
      .update(Point(0, 1), Tile(1024))
      .update(Point(1, 1), Tile(1024))
      .update(Point(3,2), Tile(8))
      .update(Point(4,2), Tile(32))
      .update(Point(2,3), Tile(8))
  }

}
