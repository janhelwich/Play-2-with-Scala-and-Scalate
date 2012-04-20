import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "Yet Another Blog Engine"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.fusesource.scalate" % "scalate-core" % "1.5.3",
    "org.fusesource.scalate" % "sbt-scalate-plugin" % "1.5.3"
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
//    scalacOptions += "-deprecation"
  )

}
