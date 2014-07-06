package com.rumblesan.fungus

import processing.core._

class Fungus extends PApplet {

  override def setup {
    size(1024, 768)
    smooth()
    frameRate(30)

    background(200, 255, 255)
  }

  override def draw {

    stroke(0)
    line(0,0, mouseX,mouseY)
    noStroke()

    fill(200, 255, 255, 50)
    rect(0, 0, width, height)

  }

}
