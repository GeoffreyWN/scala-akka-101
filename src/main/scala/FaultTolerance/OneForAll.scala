package FaultTolerance

import akka.actor.{ActorSystem, Props}

object OneForAll extends App {
  val actorSystem = ActorSystem("OneFoOneSystem")

  val supervisorActor = actorSystem.actorOf(Props[SupervisorStrategyActor], "SupervisorStrategyActor")

  supervisorActor ! "Start"
}
