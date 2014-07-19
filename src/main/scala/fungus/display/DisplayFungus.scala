package com.rumblesan.fungus.display

import com.rumblesan.fungus.befunge.{ VM }
import com.rumblesan.fungus.{ FungusMachine }
import processing.core.{ PApplet, PConstants }

object DisplayFungus {

  def draw(p: PApplet, config: DrawingConfig)(f: FungusMachine): Unit = {

    drawGrid(p, config)(f)

    drawCursor(p, config)(f)

  }

  def drawGrid(p: PApplet, config: DrawingConfig)(f: FungusMachine): Unit = {

    val vm = f.vm

    p.fill(200, 200, 200)
    for {
      x <- (0 until vm.xSize)
      y <- (0 until vm.ySize)
      (xCoord, yCoord) = cellCoords(config)(x, y)
      _ = p.rect(xCoord, yCoord, config.cellSize, config.cellSize)
    } yield Unit

  }

  def drawCursor(p: PApplet, config: DrawingConfig)(f: FungusMachine): Unit = {

    val cursor = f.cursor

    p.fill(255, 50, 50)
    val (cursorX, cursorY) = cellCoords(config)(f.cursor.x, f.cursor.y)
    p.rect(cursorX, cursorY, config.cellSize, config.cellSize)

  }

  def cellCoords(config: DrawingConfig)(xVal: Int, yVal: Int): (Int, Int) = {
    (
      config.xPadding + (xVal * config.cellSize),
      config.yPadding + (yVal * config.cellSize)
    )
  }

}

