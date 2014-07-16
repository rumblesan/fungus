package com.rumblesan.fungus

import scalaz._, Scalaz._

import processing.core.PApplet
import processing.core.PConstants._

import com.rumblesan.reaktor._
import com.rumblesan.fungus.display.{ DisplayVM, DrawingConfig }
import com.rumblesan.fungus.befunge.{ VM, Types, Counter }


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

  val vmStateSink = StateSink[KeyPress, VM]((vm, k) => {
    (k match {
      case UpKey    => (for {
                         _ <- Counter.updateCounter(Types.North)
                         _ <- Counter.moveCounter
                       } yield Unit)
      case DownKey  => (for {
                         _ <- Counter.updateCounter(Types.South)
                         _ <- Counter.moveCounter
                       } yield Unit)
      case RightKey => (for {
                         _ <- Counter.updateCounter(Types.East)
                         _ <- Counter.moveCounter
                       } yield Unit)
      case LeftKey  => (for {
                         _ <- Counter.updateCounter(Types.West)
                         _ <- Counter.moveCounter
                       } yield Unit)
      case _        => ().point[Types.VMState]
    }).exec(vm)
  }, VM(gridXSize, gridYSize))


  override def setup {
    size(screenWidth, screenHeight)
    smooth()
    frameRate(30)

    background(200, 255, 255)

  }

  override def draw {

    DisplayVM.draw(this, drawingConfig)(vmStateSink.getState)

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
