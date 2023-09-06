import akka.actor.{ActorSystem, Actor, Props}

class LoginActor extends Actor {
  def receive: Receive = {
    case x: String =>
      if (x =="Bob") {
        context.become(isAuth)
      }
  }

  def isAuth: Receive = {
    case x: String =>
      if (x == "Username") {
        println("Bob is authenticated")
      }

      if (x == "Logout") {
        println("Logout successful")
        context.become(notAuth)
      }
  }

  def notAuth: Receive = {
    case x: String =>
      if (x == "Bob") {
        context.become(isAuth)
      }
  }
}

object Login extends App {
  val actorSystem = ActorSystem("LoginAkkaSystem")

  val actor = actorSystem.actorOf(Props[LoginActor], "loginActor")

  actor ! "Bob"
  actor ! "Username"
  actor ! "Logout"
}
