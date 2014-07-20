package com.rumblesan.fungus.display

import scalaz._, Scalaz._
import processing.core.{ PApplet, PConstants }

import com.rumblesan.fungus.befunge.{ VM }
import com.rumblesan.fungus.{ FungusMachine }
import com.rumblesan.fungus.Types._
import com.rumblesan.fungus.util.GridCoord


object DisplayFungus {

  def draw(p: PApplet, config: DrawingConfig): FungusState[Unit] = {
    for {
      _ <- drawGrid(p, config)
      _ <- drawCursor(p, config)
    } yield Unit
  }

  def drawGrid(p: PApplet, config: DrawingConfig): FungusState[Unit] = {
    State.gets(f => {

      val vm = f.vm
      p.fill(200, 200, 200)
      for {
        x <- (0 until vm.xSize)
        y <- (0 until vm.ySize)
        (xCoord, yCoord) = cellCoords(config)(x, y)
        _ = DisplayInstructions.drawInstruction(p, config)(GridCoord(x, y), xCoord, yCoord)(vm)
      } yield Unit

    })
  }

  def drawCursor(
    p: PApplet, config: DrawingConfig
  ): FungusState[Unit] = {
    State.gets(f => {

      val cursor = f.cursor
      p.fill(255, 50, 50)
      val (cursorX, cursorY) = cellCoords(config)(f.cursor.x, f.cursor.y)
      p.rect(cursorX, cursorY, config.cellSize, config.cellSize)

    })
  }

  def cellCoords(config: DrawingConfig)(xVal: Int, yVal: Int): (Int, Int) = {
    (
      config.xPadding + (xVal * config.cellSize),
      config.yPadding + (yVal * config.cellSize)
    )
  }

}

