package com.rumblesan.fungus.events

import com.rumblesan.fungus._
import com.rumblesan.fungus.befunge._


case class InsertModeState(insert: Boolean)

object InsertMode {

  val keyToInstruction: Map[Char, Instruction] = Map(
    'j' -> Down,
    'k' -> Up,
    'h' -> Left,
    'l' -> Right
  )

  val stateMachine: (InsertModeState, KeyPress) => (InsertModeState, Option[FungusEvent]) = (s, k) => {

    s match {
      case InsertModeState(false) => {
        k match {
          case LetterKey('i') => (s.copy(insert = true), None)
          case _              => (s, None)
        }
      }
      case InsertModeState(true) => {
        k match {
          case LetterKey(keyVal) => (s.copy(insert = true), keyToInstruction.get(keyVal).map(InsertInstruction))
          case _                 => (s.copy(insert = false), None)
        }
      }
    }

  }


}

