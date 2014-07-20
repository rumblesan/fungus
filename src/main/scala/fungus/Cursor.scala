package com.rumblesan.fungus

import scalaz._, Scalaz._

import com.rumblesan.fungus.Types._
import com.rumblesan.fungus.util.Directions._

case class Cursor(x: Int, y: Int)

object Cursor {

  // Origin of grid is in top left so y position modifications
  // need to be switched
  def moveCursor(direction: Direction): FungusState[Unit] = State.modify { f =>
    val vm = f.vm
    val cursor = f.cursor
    val (tX, tY) = direction match {
      case MoveUp    => (cursor.x,     cursor.y - 1)
      case MoveDown  => (cursor.x,     cursor.y + 1)
      case MoveLeft  => (cursor.x - 1, cursor.y    )
      case MoveRight => (cursor.x + 1, cursor.y    )
    }
    val xMin = 0
    val yMin = 0
    val xMax = vm.xSize
    val yMax = vm.ySize
    val newX = if      (tX <  xMin) (tX + xMax)
               else if (tX >= xMax) (tX - xMax)
               else                  tX

    val newY = if      (tY <  yMin) (tY + yMax)
               else if (tY >= yMax) (tY - yMax)
               else                  tY
    f.copy(cursor = Cursor(newX, newY))
  }

}

