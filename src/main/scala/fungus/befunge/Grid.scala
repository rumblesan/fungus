package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._

import Types._
import com.rumblesan.fungus.util.GridCoord


object Grid {

  def setGridCell(gridCoord: GridCoord, inst: Instruction): VMState[Unit] = {
    State.modify(vm => {
      val x = Math.abs(gridCoord.x) % vm.xSize
      val y = Math.abs(gridCoord.y) % vm.ySize
      val g = vm.grid
      vm.copy(grid = g.updated(x, (g(x).updated(y, inst))))
    })
  }

  def getGridCell(gridCoord: GridCoord): VMState[Instruction] = {
    State.gets(vm => {
      val x = Math.abs(gridCoord.x) % vm.xSize
      val y = Math.abs(gridCoord.y) % vm.ySize
      vm.grid(x)(y)
    })
  }

}

