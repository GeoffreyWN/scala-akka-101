package FaultTolerance

import akka.actor.Actor

class Printer extends Actor {
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("PrinterActor: I am restarting now!")
  }

  def receive: Receive = {
    case msg: String => println(msg)
    case msg: Int => 1 / 0
  }
}
