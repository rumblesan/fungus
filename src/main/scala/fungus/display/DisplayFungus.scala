package com.rumblesan.fungus.display

import scalaz._, Scalaz._
import processing.core.{ PApplet, PConstants }

import com.rumblesan.fungus.befunge.{ VM }
import com.rumblesan.fungus.{ FungusMachine }
import com.rumblesan.fungus.Types._
import com.rumblesan.fungus.util.{ GridCoord, CanvasCoord }


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
        gridCoords = GridCoord(x, y)
        canvasCoords = calculateCanvasCoords(config)(gridCoords)
        _ = DisplayInstructions.drawInstruction(p, config)(gridCoords, canvasCoords)(vm)
      } yield Unit

    })
  }

  def drawCursor(
    p: PApplet, config: DrawingConfig
  ): FungusState[Unit] = {
    State.gets(f => {

      val cursor = f.cursor
      p.fill(255, 50, 50)
      val canvasCoords = calculateCanvasCoords(config)(GridCoord(f.cursor.x, f.cursor.y))
      p.rect(canvasCoords.x, canvasCoords.y, config.cellSize, config.cellSize)

    })
  }

  def calculateCanvasCoords(config: DrawingConfig)(gridCoords: GridCoord): CanvasCoord = {
    CanvasCoord(
      config.xPadding + (gridCoords.x * config.cellSize),
      config.yPadding + (gridCoords.y * config.cellSize)
    )
  }

}

