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
  "mysql" % "mysql-connector-java" % "5.1.41",
  //"com.h2database"  %  "h2"                           % "1.4.200",
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.5.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.5.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"
)

enablePlugins(ScalikejdbcPlugin)

unmanagedResourceDirectories in Test +=  baseDirectory.value / "target/web/public/test"

      