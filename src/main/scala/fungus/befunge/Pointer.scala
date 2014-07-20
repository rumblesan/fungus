package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.util.Directions._
import com.rumblesan.fungus.util.GridCoord


case class Pointer(coords: GridCoord, direction: Direction)

object Pointer {

  // Origin of grid is in top left so y position modifications
  // need to be switched
  def movePointer: VMState[Unit] = State.modify { vm =>
    val pointer = vm.pointer
    val (tX, tY) = pointer.direction match {
      case MoveUp    => (pointer.coords.x,     pointer.coords.y - 1)
      case MoveDown  => (pointer.coords.x,     pointer.coords.y + 1)
      case MoveLeft  => (pointer.coords.x - 1, pointer.coords.y    )
      case MoveRight => (pointer.coords.x + 1, pointer.coords.y    )
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
    vm.copy(pointer = Pointer(GridCoord(newX, newY), pointer.direction))
  }

  def updatePointer(newDirection: Direction): VMState[Unit] = State.modify { vm =>
    vm.copy(
      pointer = vm.pointer.copy(direction = newDirection)
    )
  }

}


