scalaVersion := "2.13.6"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "nano-cache",
    Compile / scalaSource := baseDirectory.value / "src",
    libraryDependencies ++= Seq(
      guice,
      "redis.clients"      % "jedis"       % "3.6.1",
      "com.typesafe.play" %% "play-server" % "2.8.8",
      "org.scalactic"     %% "scalactic"   % "3.2.9",
      // Test
      "org.scalatest" %% "scalatest" % "3.2.9" % "test"
    )
  )
