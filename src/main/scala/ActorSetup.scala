import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

object ActorSetup extends App {
  implicit val timeout: Timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem("AkkaGroundSystem")

  val actor = actorSystem.actorOf(Props[AddActor], "addActor")

  val future = (actor ? 5).mapTo[Int]

  val sum = Await.result(future, 10 seconds)

  println(sum)


}

class AddActor extends Actor {
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum += x
      sender ! sum

    case _ => println("Error! arg could not be added!")
  }
}