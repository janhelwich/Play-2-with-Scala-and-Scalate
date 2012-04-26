import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "Yet Another Blog Engine"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.fusesource.scalate" % "scalate-core" % "1.5.3",
    "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
    "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT",
    "org.mockito" % "mockito-all" % "1.9.0"
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
  )

}
