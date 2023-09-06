package FaultTolerance

import akka.actor.{Actor, ActorSystem, Props}


case object CreateChild
case class Greet(msg: String)

class ChildActor extends  Actor {
  def receive: Receive = {
    case Greet(msg: String) => println(s"My parentActor: [${self.path.parent}] greeted me [${self.path}] with a message ${msg} ")
  }
}

class ParentActor extends Actor {
  def receive: Receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor], "childACTOR")

      child ! Greet("Hey my child!")
  }
}

object ParentChild extends App {
  val actorSystem = ActorSystem("ParentChildActorSYS")

  val parentActor = actorSystem.actorOf(Props[ParentActor], "parentACTOR")

  parentActor ! CreateChild
}