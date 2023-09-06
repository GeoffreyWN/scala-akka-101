package Routing

// BalancingPool Routing: Attempts to redistribute work from busy actors to the idle actors. All actors share the same mailbox
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.BalancingPool

class BalancingPoolActor extends Actor {
  def receive: Receive = {
    case msg: String => println(s"BalancingPool Routing: I am ${self.path.name} and message is $msg")
    case _ => println("Invalid Message")
  }
}

object BalancingPoolApproach extends App {
  val actorSystem = ActorSystem("RouteSys")

  val routerActor = actorSystem.actorOf(BalancingPool(15).props(Props[BalancingPoolActor]), "balancingPoolRouterACTOR")

  for (i <- 1 to 15) {
    routerActor !  "Hello $i"
  }
}