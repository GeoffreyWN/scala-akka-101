package Routing

// Redistributes the messages one by one round the group and at the end the cycle is repeated (messages are distributed evenly)
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinPool

class RoundRobinRoutingActor extends Actor {
  def receive: Receive = {
    case msg: String => println(s"RoundRobinPool routing: I am ${self.path.name} and message is $msg")
    case _ => println("Invalid Message")
  }
}

object RoundRobinApproach extends App {
  val actorSystem = ActorSystem("RouteSys")

  val routerActor = actorSystem.actorOf(RoundRobinPool(10).props(Props[RoundRobinRoutingActor]), "balancingPoolRouterACTOR")

  for (i <- 1 to 20) {
    routerActor !  "Hello $i"
    Thread.sleep(1000) // sleep for demo purposes
  }
}