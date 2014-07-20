package com.rumblesan.fungus.display

import scalaz._, Scalaz._
import processing.core.{ PApplet, PImage }

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.befunge._
import com.rumblesan.fungus.util.GridCoord


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
    gridCoords: GridCoord, xCoord: Int, yCoord: Int
  ): VMState[Unit] = {
    Grid.getGridCell(gridCoords).map( i =>
      i match {
        case Up    => p.image(config.instructionTiles(Up), xCoord, yCoord)
        case Down  => p.image(config.instructionTiles(Down), xCoord, yCoord)
        case Left  => p.image(config.instructionTiles(Left), xCoord, yCoord)
        case Right => p.image(config.instructionTiles(Right), xCoord, yCoord)
        case NOP   => p.rect(xCoord, yCoord, config.cellSize, config.cellSize)
        case _     => p.rect(xCoord, yCoord, config.cellSize, config.cellSize)
      }
    )
  }

}

