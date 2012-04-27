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

//import com.mongodb.casbah.commons.Imports._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.MongoURI
import play.api.Play

case class Post(title: String,  posted: Date, content: String, _id: ObjectId = new ObjectId)

object Post{
  val collection = {
    val baseuri = new com.mongodb.MongoURI("mongodb://Jan:Hawk20@staff.mongohq.com:10008/app4085981 ")
    val uri = new MongoURI(baseuri)
    MongoConnection(baseuri)("heroku_app4085981")("posts")
  }
  private[helwich] var dao = new SalatDAO[Post, ObjectId](collection = collection) {}

  def findAll() = {
    dao.find(MongoDBObject.empty).toList
  }

  def create(post: Post): Unit = {
    dao.insert(post)
  }
}