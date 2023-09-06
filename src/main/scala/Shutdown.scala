import akka.actor.{ActorSystem, Actor, Props, PoisonPill}

case object Stop

class ShutdownActor extends Actor {
  def receive: Receive = {
    case msg: String => println(s"$msg")
    case Stop => context.stop(self)
  }
}


object ShutdownActor extends App {
  val actorSystem = ActorSystem("ShutdownAkkaSystem")

  val shutdownActor1 = actorSystem.actorOf(Props[ShutdownActor], "shutdownActor1")

  shutdownActor1 ! "Test me"
  shutdownActor1 ! PoisonPill // shutdownActor1 ! Stop //
  shutdownActor1 ! "Test Again"

}