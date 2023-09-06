package  Scheduling

import akka.actor.ActorSystem

import scala.concurrent.duration._
import scala.language.postfixOps

object ScheduleOperation extends App {
  val system = ActorSystem("ScheduleSys")
  import system.dispatcher // essentially handles when things are running in the created ActorSystem

  system.scheduler.scheduleOnce(10 seconds) {
    println("Say Hi once after 10 secs")
  }

  system.scheduler.scheduleAtFixedRate(11 seconds, 2 seconds)(() => println("Repeating Say Hi."))


}