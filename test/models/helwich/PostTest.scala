package models.helwich

import play.api.Play.current
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.test.FakeApplication
import play.api.test.Helpers._
import java.util.Date
import com.novus.salat.dao._

import com.mongodb.casbah.commons.Imports._


class PostTest extends Specification with Mockito {

  "The posts companion object" should {
    "pass the post to the insert method of the mongo helper" in {
      running(FakeApplication()) {
        Post.dao = mock[SalatDAO[Post, ObjectId]]
        val post = Post("title", new Date(), "content")
        Post.create(post)
        there was one(Post.dao).insert(post)
      }
    }
  }
}
