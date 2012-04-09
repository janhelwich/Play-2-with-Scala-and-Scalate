package models.helwich

import org.specs2.mutable.Specification
import play.api.test.FakeApplication
import play.api.test.Helpers._
import java.util.Date
import anorm.Id

class PostTest extends Specification {

  "Posts" should {
    "be persistable" in {
      running(FakeApplication()) {
        val user = User.create( User("mail", "pwd", "name") )
        Post.create(Post("title", new Date(), "content", user))
        val allPosts = Post.findAll()
        allPosts.size must beEqualTo (1)
        allPosts(0).author.fullname must be ("name")
      }
    }
  }


}
