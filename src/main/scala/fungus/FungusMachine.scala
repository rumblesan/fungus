package com.rumblesan.fungus

import scalaz._, Scalaz._

import com.rumblesan.fungus.befunge.{ VM }

case class FungusMachine(vm: VM, cursor: Cursor)

