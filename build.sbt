name := "hello_play_scalikejdbc"
 
version := "1.0" 
      
lazy val `hello_play_scalikejdbc` = (project in file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(SbtWeb)


resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.2.+",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.2.+",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.2"
)

unmanagedResourceDirectories in Test +=  baseDirectory.value / "target/web/public/test"

      