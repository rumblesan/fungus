package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.util.Directions._


case class Pointer(xPos: Int, yPos: Int, direction: Direction)

object Pointer {

  // Origin of grid is in top left so y position modifications
  // need to be switched
  def movePointer: VMState[Unit] = State.modify { vm =>
    val pointer = vm.pointer
    val (tX, tY) = pointer.direction match {
      case MoveUp    => (pointer.xPos,     pointer.yPos - 1)
      case MoveDown  => (pointer.xPos,     pointer.yPos + 1)
      case MoveLeft  => (pointer.xPos - 1, pointer.yPos    )
      case MoveRight => (pointer.xPos + 1, pointer.yPos    )
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
    vm.copy(pointer = Pointer(newX, newY, pointer.direction))
  }

  def updatePointer(newDirection: Direction): VMState[Unit] = State.modify { vm =>
    vm.copy(
      pointer = vm.pointer.copy(direction = newDirection)
    )
  }

}


