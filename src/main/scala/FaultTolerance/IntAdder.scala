package FaultTolerance

import akka.actor.Actor

class IntAdder extends Actor {
  var x = 0

  def receive: Receive = {
    case msg: Int => x = x + msg
      println(x)

    case msg: String => throw new IllegalArgumentException()
  }

  override def postStop(): Unit = {
    println("IntAdder: I am stopping!")
  }
}
