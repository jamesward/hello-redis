import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit

import akka.actor.{Props, ActorSystem}
import redis.RedisClient
import redis.actors.RedisSubscriberActorWithCallback
import redis.api.pubsub.Message
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

object Main extends App {
  val system = ActorSystem("MyActorSystem")

  val redisAddress = new InetSocketAddress("localhost", 6379)

  val redis = RedisClient(redisAddress.getHostName, redisAddress.getPort)(system)

  def send = redis.publish("hello", System.currentTimeMillis())

  system.scheduler.schedule(Duration.Zero, 2.seconds)(send)(ExecutionContext.global)

  def sayHello(m: Message) = println(m)

  system.actorOf(Props(new RedisSubscriberActorWithCallback(redisAddress, Seq("hello"), Nil, sayHello, println)))

  system.awaitTermination()
}