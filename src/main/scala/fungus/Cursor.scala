package com.rumblesan.fungus

import scalaz._, Scalaz._

import Types._

case class Cursor(x: Int, y: Int)

object Cursor {

  def moveCursor(direction: String): FungusState[Unit] = State.modify { f =>
    val vm = f.vm
    val cursor = f.cursor
    val (tX, tY) = direction match {
      case "up"    => (cursor.x,     cursor.y + 1)
      case "down"  => (cursor.x,     cursor.y - 1)
      case "right" => (cursor.x + 1, cursor.y    )
      case "left"  => (cursor.x - 1, cursor.y    )
    }
    val xMin = 0
    val yMin = 0
    val xMax = vm.xSize
    val yMax = vm.ySize
    val newX = if      (tX < xMin) (tX + xMax)
               else if (tX > xMax) (tX - xMax)
               else                 tX

    val newY = if      (tY < yMin) (tY + yMax)
               else if (tY > yMax) (tY - yMax)
               else                 tY
    f.copy(cursor = Cursor(newX, newY))
  }

}

