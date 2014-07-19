package com.rumblesan.fungus

import scalaz._, Scalaz._

import processing.core.PApplet
import processing.core.PConstants._

import com.rumblesan.reaktor._
import com.rumblesan.fungus.display.{ DisplayFungus, DrawingConfig }
import com.rumblesan.fungus.befunge.{ VM, Counter }
import com.rumblesan.fungus.befunge.Types._
import com.rumblesan.fungus.Types._


class Fungus extends PApplet {

  val gridXSize    = 30
  val gridYSize    = 10
  val screenWidth  = 1024
  val screenHeight = 768
  val gridCellSize = 14

  val drawingConfig = DrawingConfig(
    gridCellSize,
    (screenWidth - (gridXSize * gridCellSize)) / 2,
    (screenHeight - (gridYSize * gridCellSize)) / 2
  )

  val vmStateSink = StateSink[KeyPress, FungusMachine]((f, k) => {
    (k match {
      case UpKey    => Cursor.moveCursor("up")
      case DownKey  => Cursor.moveCursor("up")
      case RightKey => Cursor.moveCursor("up")
      case LeftKey  => Cursor.moveCursor("up")
      case _        => ().point[FungusState]
    }).exec(f)
  }, FungusMachine(VM(gridXSize, gridYSize), Cursor(0, 0)))


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
    vmStateSink(
      if (key == CODED) {
             if (keyCode == UP   ) UpKey
        else if (keyCode == DOWN ) DownKey
        else if (keyCode == LEFT ) LeftKey
        else if (keyCode == RIGHT) RightKey
        else                       UnknownKey
      } else {
        UnknownKey
      }
    )
  }

}
