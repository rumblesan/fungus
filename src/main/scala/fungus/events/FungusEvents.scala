package com.rumblesan.fungus.events

import com.rumblesan.fungus.befunge._


sealed trait FungusEvent

case object NoEvent         extends FungusEvent

case object MoveCursorUp    extends FungusEvent
case object MoveCursorDown  extends FungusEvent
case object MoveCursorLeft  extends FungusEvent
case object MoveCursorRight extends FungusEvent

case class InsertInstruction(i: Instruction) extends FungusEvent

