package com.rumblesan.fungus

import scalaz._, Scalaz._


object Types {

  type FungusState[A] = State[FungusMachine, A]

}


