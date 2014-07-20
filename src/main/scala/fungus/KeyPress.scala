package com.rumblesan.fungus

sealed trait KeyPress

case object UnknownKey         extends KeyPress
case object UpKey              extends KeyPress
case object DownKey            extends KeyPress
case object LeftKey            extends KeyPress
case object RightKey           extends KeyPress
case class  LetterKey(c: Char) extends KeyPress

