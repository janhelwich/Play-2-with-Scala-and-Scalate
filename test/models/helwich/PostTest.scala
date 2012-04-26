package models.helwich

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.test.FakeApplication
import play.api.test.Helpers._
import java.util.Date
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._

import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.MongoConnection


class PostTest extends Specification with Mockito{
  Post.dao = mock[SalatDAO[Post, ObjectId]]

  "The posts companion object" should {
    "pass the post to the insert method of the mongo helper" in {
      val post = Post("title", new Date(), "content", null)
      Post.create(post)
      there was one(Post.dao).insert(post)
    }
  }
}
