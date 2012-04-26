package models.helwich

import java.util.Date
import play.api.db._
import play.api.Play.current
import anorm.Sql._
import anorm._
import anorm.SqlParser._
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._

import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.MongoConnection

case class Post(title: String,  posted: Date, content: String, author: User, _id: ObjectId = new ObjectId)

object Post{
  val collection = MongoConnection()("blog_db")("posts")
  private[helwich] var dao = new SalatDAO[Post, ObjectId](collection = collection) {}

  def findAll() = {
    val results = dao.find(MongoDBObject.empty)
    results.toList
  }

  def create(post: Post): Unit = {
    dao.insert(post)
  }


}