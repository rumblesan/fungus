package com.rumblesan.fungus.display

import scalaz._, Scalaz._
import processing.core.{ PApplet, PImage }

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.befunge._
import com.rumblesan.fungus.util.{ GridCoord, CanvasCoord }


object DisplayInstructions {

  def loadInstructionTiles(p: PApplet): Map[Instruction, PImage] = Map(
    Up    -> p.loadImage("instruction-tiles/up.png"),
    Down  -> p.loadImage("instruction-tiles/down.png"),
    Left  -> p.loadImage("instruction-tiles/left.png"),
    Right -> p.loadImage("instruction-tiles/up.png")
  )

  def drawInstruction(
    p: PApplet, config: DrawingConfig
  )(
    gridCoords: GridCoord, canvasCoords: CanvasCoord
  ): VMState[Unit] = {
    Grid.getGridCell(gridCoords).map( i =>
      i match {
        case Up    => p.image(config.instructionTiles(Up), canvasCoords.x, canvasCoords.y)
        case Down  => p.image(config.instructionTiles(Down), canvasCoords.x, canvasCoords.y)
        case Left  => p.image(config.instructionTiles(Left), canvasCoords.x, canvasCoords.y)
        case Right => p.image(config.instructionTiles(Right), canvasCoords.x, canvasCoords.y)
        case NOP   => p.rect(canvasCoords.x, canvasCoords.y, config.cellSize, config.cellSize)
        case _     => p.rect(canvasCoords.x, canvasCoords.y, config.cellSize, config.cellSize)
      }
    )
  }

}

