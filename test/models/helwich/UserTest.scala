package models.helwich

import play.api.Play.current
import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test.FakeApplication
import anorm._
import play.api.db._

class UserTest extends Specification {

  "Users" should {
    "be persistable" in {
      running(FakeApplication()) {
        User.create(User("email", "pwd", null))
        User.create(User("anotheremail", "pwd", "myname"))
        val allUsers: Seq[User] = User.findAll()
        allUsers must have size 2
        allUsers(0).id.get must beEqualTo(1)
        allUsers(1).id.get must beEqualTo(2)
      }
    }

    "contain the PK after persistent creation" in {
      running(FakeApplication()) {
        val persisted: User = User.create(User("anotheremail", "pwd", "myname"))
        persisted.id.get must beGreaterThan (0)
      }
    }
    
    "be findable via the PK id" in {
      running(FakeApplication()) {
        val user = User.create(User("mail", "pwd", "name"))
        val foundUser = User.findBy(user.id)

        foundUser.fullname must beEqualTo (user.fullname)
      }
    }
  }
}
