package models.helwich

import play.api.Play.current
import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test.FakeApplication._
import play.api.db.DB
import anorm._
import play.api.test.FakeApplication

class DBEvolutionsTest extends Specification {

  "Evolutions" should {
    "be applied without errors" in {
      evolutionFor("default")
      running(FakeApplication()) {
        DB.withConnection {
          implicit connection =>
            SQL("select count(1) from myuser").execute()
            SQL("select count(1) from posts").execute()
        }
      }
      success
    }
  }

}
