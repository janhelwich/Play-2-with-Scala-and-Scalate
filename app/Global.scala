import models.helwich.{Post, User}
import org.joda.time.DateTime
import play.api._

object Global extends GlobalSettings {

  override def onStart(app: play.api.Application) {
    User.create(new User("u1", "pwd", "name1"))
  }

}
