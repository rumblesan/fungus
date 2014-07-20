package com.rumblesan.fungus.befunge

import scalaz._, Scalaz._


object Types {

  type Grid = Vector[Vector[Instruction]]

  type Stack = List[Integer]

  type VMState[A] = State[VM, A]

}

