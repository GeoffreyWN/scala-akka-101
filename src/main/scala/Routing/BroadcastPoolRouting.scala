package Routing

// BroadcastPool Routing: send exact same message to a number of different actors in the pool. Case scenario, sending push notifications or updates to different devices
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.BroadcastPool

class BroadcastPoolActor extends Actor {
  def receive: Receive = {
    case msg: String => println(s"BroadcastPool Routing: I am ${self.path.name} and message is $msg")
    case _ => println("Invalid Message")
  }
}

object BroadcastPoolApproach extends App {
  val actorSystem = ActorSystem("RouteSys")

  val routerActor = actorSystem.actorOf(BroadcastPool(15).props(Props[BroadcastPoolActor]), "broadcastPoolRouterACTOR")

    routerActor !  "Hello $i"

}