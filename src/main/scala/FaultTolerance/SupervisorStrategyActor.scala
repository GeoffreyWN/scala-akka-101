package FaultTolerance

import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SupervisorStrategyActor extends Actor {
  // AllForOneStrategy: implement it when all child actors depend on each other (an exception happens in one child then all the other children are given the same supervisor strategy)
  // OneForOneStrategy: implement it when the child actors function independently

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute, loggingEnabled = true) {
    case _: ArithmeticException => SupervisorStrategy.Restart
    case _: IllegalArgumentException => SupervisorStrategy.Stop
    case _: NullPointerException => SupervisorStrategy.Resume
    case _: Exception => SupervisorStrategy.Escalate
  }

  val printerActor = context.actorOf(Props[Printer], "printerActor")
  val intAdderActor = context.actorOf(Props[IntAdder], "intAdderActor")

  def receive: Receive = {
    case "Start" =>
      printerActor ! "printerActor Hello"
      printerActor ! 10

      intAdderActor ! 10
      intAdderActor ! 10
      intAdderActor ! "intAdderActor Hello"
  }

}
