name := "hello-redis"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"

libraryDependencies ++= Seq(
  "com.etaty.rediscala" %% "rediscala" % "1.4.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11"
)

sourceDirectory := file(".")