package com.rumblesan.fungus

import scalaz._, Scalaz._

import processing.core.PApplet
import processing.core.PConstants._

import com.rumblesan.reaktor._
import com.rumblesan.fungus.display.{ DisplayFungus, DrawingConfig, DisplayInstructions }
import com.rumblesan.fungus.befunge.{ VM, Pointer, Grid }
import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.befunge._
import com.rumblesan.fungus.Types._
import com.rumblesan.fungus.util.Directions._
import com.rumblesan.fungus.util.GridCoord
import com.rumblesan.fungus.events._


class Fungus extends PApplet {

  val gridXSize    = 30
  val gridYSize    = 10
  val screenWidth  = 1024
  val screenHeight = 768
  val gridCellSize = 20

  val drawingConfig = DrawingConfig(
    gridCellSize,
    (screenWidth - (gridXSize * gridCellSize)) / 2,
    (screenHeight - (gridYSize * gridCellSize)) / 2,
    DisplayInstructions.loadInstructionTiles(this)
  )

  val vmStateSink = FungusEventSystem.vmStateSink(
    FungusMachine(
      VM(gridXSize, gridYSize),
      Cursor(GridCoord(0, 0))
    )
  )

  val eventSystem = FungusEventSystem(vmStateSink)

  override def setup {
    size(screenWidth, screenHeight)
    smooth()
    frameRate(30)

    background(200, 255, 255)

  }

  override def draw {

    DisplayFungus.draw(this, drawingConfig)(vmStateSink.getState)

  }

  override def keyPressed {
    eventSystem(
      if (key == CODED) {
             if (keyCode == UP   ) UpKey
        else if (keyCode == DOWN ) DownKey
        else if (keyCode == LEFT ) LeftKey
        else if (keyCode == RIGHT) RightKey
        else                       UnknownKey
      } else {
        LetterKey(key)
      }
    )

  }

}
