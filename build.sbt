import com.mojolly.scalate.ScalatePlugin._

seq(scalateSettings:_*)

scalateTemplateDirectory in Compile <<= (baseDirectory) { _ / "src/main/webapp/WEB-INF" }

scalateImports ++= Seq(
  "import scalaz._",
  "import Scalaz._",
  "import io.backchat.oauth2._",
  "import OAuth2Imports._",
  "import model._"
)

scalateBindings ++= Seq(
  Binding("flash", "scala.collection.Map[String, Any]", defaultValue = "Map.empty"),
  Binding("session", "org.scalatra.Session"),
  Binding("sessionOption", "Option[org.scalatra.Session]"),
  Binding("params", "scala.collection.Map[String, String]"),
  Binding("multiParams", "org.scalatra.MultiParams"),
  Binding("userOption", "Option[ResourceOwner]", defaultValue = "None"),
  Binding("user", "ResourceOwner", defaultValue = "null"),
  Binding("isAnonymous", "Boolean", defaultValue = "true"),
  Binding("isAuthenticated", "Boolean", defaultValue = "false"))