package  Scheduling


import akka.actor.{Actor, ActorSystem, Cancellable, Props}

import scala.concurrent.duration._
import scala.language.postfixOps

class MessageActor2 extends Actor {

  var i = 3
  override def receive: Receive = {
    case "message" => println("Scheduled Message")
      i = i - 1
      if(i==0) {
        CancelSchedule.cancellable.cancel()
      }
  }
}

object CancelSchedule extends App {
  val system =  ActorSystem("Schedule-Sys")
  import system.dispatcher

  val messageActor2 = system.actorOf(Props[MessageActor2], "messageActor2")

  val cancellable: Cancellable = system.scheduler.scheduleAtFixedRate(0 seconds, 2 seconds, messageActor2, "message")
}