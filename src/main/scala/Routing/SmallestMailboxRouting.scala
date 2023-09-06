package Routing

// SmallestMailboxPool Routing: Work is sent to the actor with the least messages in their mailbox
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.SmallestMailboxPool

class SmallestMailboxRouting extends Actor {
  def receive: Receive = {
    case msg: String => println(s"SmallestMailboxPool Routing :I am ${self.path.name} and message is $msg")
    case _ => println("Invalid Message")
  }
}

object SmallestActor extends App {
  val actorSystem = ActorSystem("RouteSys")

  val routerActor = actorSystem.actorOf(SmallestMailboxPool(15).props(Props[SmallestMailboxRouting]), "smallestMailboxRouterACTOR")

  for (i <- 1 to 15) {
    routerActor !  "Hello $i"
  }
}