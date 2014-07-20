package com.rumblesan.fungus.util

import scalaz._, Scalaz._

object Directions {

  sealed trait Direction
  case object MoveUp    extends Direction
  case object MoveDown  extends Direction
  case object MoveRight extends Direction
  case object MoveLeft  extends Direction

}

