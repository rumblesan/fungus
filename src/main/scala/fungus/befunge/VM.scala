package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import Types._
import com.rumblesan.fungus.util.Directions._


case class VM(
  counter: Counter,
  grid: Grid,
  stack: Stack,
  xSize: Int,
  ySize: Int
)

object VM {

  def apply(xVal: Int, yVal: Int): VM = {
    VM(
      Counter(0, 0, MoveRight),
      Vector.fill[Instruction](xVal, yVal)(NOP),
      List.empty[Integer],
      xVal,
      yVal
    )
  }

  def getInstruction: VMState[Instruction] = {
    for {
      vm <- State.get
      instruction <- Grid.getGridCell(vm.counter.xPos, vm.counter.yPos)
    } yield instruction
  }

  def executeInstruction(inst: Instruction): VMState[Unit] = {
    inst match {

      case Up    => Counter.updateCounter(MoveUp)
      case Down  => Counter.updateCounter(MoveDown)
      case Right => Counter.updateCounter(MoveRight)
      case Left  => Counter.updateCounter(MoveLeft)

      case NOP => State.modify(vm => vm)
      case _ => State.modify(vm => vm)

    }
  }

  def runSimulationTurn: VMState[Unit] = {
    for {
      instruction <- getInstruction
      _ <- executeInstruction(instruction)
      _ <- Counter.moveCounter
    } yield ()
  }

}

