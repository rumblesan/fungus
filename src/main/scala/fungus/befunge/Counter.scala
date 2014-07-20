package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.util.Directions._


case class Counter(xPos: Int, yPos: Int, direction: Direction)

object Counter {

  // Origin of grid is in top left so y position modifications
  // need to be switched
  def moveCounter: VMState[Unit] = State.modify { vm =>
    val counter = vm.counter
    val (tX, tY) = counter.direction match {
      case MoveUp    => (counter.xPos,     counter.yPos - 1)
      case MoveDown  => (counter.xPos,     counter.yPos + 1)
      case MoveLeft  => (counter.xPos + 1, counter.yPos    )
      case MoveRight => (counter.xPos - 1, counter.yPos    )
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
    vm.copy(counter = Counter(newX, newY, counter.direction))
  }

  def updateCounter(newDirection: Direction): VMState[Unit] = State.modify { vm =>
    vm.copy(
      counter = vm.counter.copy(direction = newDirection)
    )
  }

}


