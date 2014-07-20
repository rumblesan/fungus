package com.rumblesan.fungus.display

import scalaz._, Scalaz._
import processing.core.{ PApplet, PImage }

import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.befunge._


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
    xPos: Int, yPos: Int
  ): VMState[Unit] = {
    Grid.getGridCell(xPos, yPos).map( i =>
      i match {
        case Up    => p.rect(xPos, yPos, config.cellSize, config.cellSize)
        case Down  => p.rect(xPos, yPos, config.cellSize, config.cellSize)
        case Left  => p.rect(xPos, yPos, config.cellSize, config.cellSize)
        case Right => p.rect(xPos, yPos, config.cellSize, config.cellSize)
        case NOP   => p.rect(xPos, yPos, config.cellSize, config.cellSize)
        case _     => p.rect(xPos, yPos, config.cellSize, config.cellSize)
      }
    )
  }

}

