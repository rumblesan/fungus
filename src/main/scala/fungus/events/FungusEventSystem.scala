package com.rumblesan.fungus.events

import scalaz._, Scalaz._

import com.rumblesan.reaktor._

import com.rumblesan.fungus.{ FungusMachine, Cursor }
import com.rumblesan.fungus.Types._
import com.rumblesan.fungus._
import com.rumblesan.fungus.befunge.{ VM }
import com.rumblesan.fungus.util.Directions._


object FungusEventSystem {

  def vmStateSink(initialFungusState: FungusMachine) = StateSink[FungusEvent, FungusMachine]((f, k) => {
    (k match {
      case MoveCursorUp    => Cursor.moveCursor(MoveUp)
      case MoveCursorDown  => Cursor.moveCursor(MoveDown)
      case MoveCursorRight => Cursor.moveCursor(MoveRight)
      case MoveCursorLeft  => Cursor.moveCursor(MoveLeft)
      case _               => ().point[FungusState]
    }).exec(f)
  }, initialFungusState)


  def apply(vmStateSink: StateStream[FungusEvent, FungusMachine, Nothing]) = {

    val insertModeAggregator = StateStream(
      InsertMode.stateMachine,
      InsertModeState(false),
      vmStateSink.apply
    )

    val cursorMovementEventStream = vmStateSink.push[KeyPress](key => {
      key match {
        case UpKey    => MoveCursorUp
        case DownKey  => MoveCursorDown
        case LeftKey  => MoveCursorLeft
        case RightKey => MoveCursorRight
        case _        => NoEvent
      }
    })

    EventStreamOps.fanOut(
      insertModeAggregator,
      cursorMovementEventStream
    )

  }

}

