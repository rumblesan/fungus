package com.rumblesan.fungus.display

import processing.core.PImage
import com.rumblesan.fungus.befunge._


case class DrawingConfig(
  cellSize: Int,
  xPadding: Int,
  yPadding: Int,
  instructionTiles: Map[Instruction, PImage]
)

