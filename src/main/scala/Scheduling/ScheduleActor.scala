package  Scheduling


import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.duration._
import scala.language.postfixOps

class MessageActor extends Actor {
  override def receive: Receive = {
    case "message1" => println("First Message")
    case "message2" => println("Second Message")
  }
}


object ScheduleActor extends App {
  val system = ActorSystem("ScheduleSys")
  import system.dispatcher
  val messageActor = system.actorOf(Props[MessageActor], "messageActor")

  system.scheduler.scheduleOnce(10 seconds, messageActor, "message1")
  system.scheduler.scheduleAtFixedRate(11 seconds, 2 seconds, messageActor, "message2")

}