package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import Types._
import com.rumblesan.fungus.util.Directions._
import com.rumblesan.fungus.util.GridCoord


case class VM(
  pointer: Pointer,
  grid: Grid,
  stack: Stack,
  xSize: Int,
  ySize: Int
)

object VM {

  def apply(xVal: Int, yVal: Int): VM = {
    VM(
      Pointer(GridCoord(0, 0), MoveRight),
      Vector.fill[Instruction](xVal, yVal)(NOP),
      List.empty[Integer],
      xVal,
      yVal
    )
  }

  def getInstruction: VMState[Instruction] = {
    for {
      vm <- State.get
      instruction <- Grid.getGridCell(vm.pointer.coords)
    } yield instruction
  }

  def executeInstruction(inst: Instruction): VMState[Unit] = {
    inst match {

      case Up    => Pointer.updatePointer(MoveUp)
      case Down  => Pointer.updatePointer(MoveDown)
      case Right => Pointer.updatePointer(MoveRight)
      case Left  => Pointer.updatePointer(MoveLeft)

      case NOP => State.modify(vm => vm)
      case _ => State.modify(vm => vm)

    }
  }

  def runSimulationTurn: VMState[Unit] = {
    for {
      instruction <- getInstruction
      _ <- executeInstruction(instruction)
      _ <- Pointer.movePointer
    } yield ()
  }

}

