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

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.MongoURI
import play.api.Play
import java.net.URI

case class Post(title: String,  posted: Date, content: String, _id: ObjectId = new ObjectId)

object Post{
  val db = {
    Play.configuration.getString("mongo.uri") match {
      case Some(uriTxt) => {
        val uri = new URI(uriTxt)
        val db = MongoConnection(uri.getHost, uri.getPort).getDB(uri.getPath.replace("/",""))
        val user_pwd = uri.getUserInfo.split(":")
        if (!db.authenticate(user_pwd(0), user_pwd(1))) throw new Exception("No auth of mongo db possible")
        db
      }
      case _ => MongoConnection()("yabe")
    }

  }
  private[helwich] var dao = new SalatDAO[Post, ObjectId](collection = db.getCollection("posts").asScala) {}

  def findAll() = {
    dao.find(MongoDBObject.empty).toList
  }

  def create(post: Post): Unit = {
    dao.insert(post)
  }
}