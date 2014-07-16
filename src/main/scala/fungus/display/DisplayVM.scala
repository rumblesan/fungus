package com.rumblesan.fungus.display

import com.rumblesan.fungus.befunge.{ VM }
import processing.core.{ PApplet, PConstants }

object DisplayVM {

  def draw(p: PApplet, config: DrawingConfig)(vm: VM): Unit = {

    p.fill(200, 200, 200)
    for {
      x <- (0 until vm.xSize)
      y <- (0 until vm.ySize)
      (xCoord, yCoord) = cellCoords(config)(x, y)
      _ = p.rect(xCoord, yCoord, config.cellSize, config.cellSize)
    } yield Unit

    p.fill(255, 50, 50)
    val (counterX, counterY) = cellCoords(config)(vm.counter.xPos, vm.counter.yPos)
    p.rect(counterX, counterY, config.cellSize, config.cellSize)

  }

  def cellCoords(config: DrawingConfig)(xVal: Int, yVal: Int): (Int, Int) = {
    (
      config.xPadding + (xVal * config.cellSize),
      config.yPadding + (yVal * config.cellSize)
    )
  }

}

