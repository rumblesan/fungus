package com.rumblesan.fungus

import processing.core._

import com.rumblesan.reaktor._


class Fungus extends PApplet {

  val s = StateSink[Int, Int]((s, e) => s + 1, 0)
  val keyEventStream = s <<= ((k: Char) => 1)

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
    println(s.getState)

  }

  override def keyPressed {
    keyEventStream(key)
  }

}
