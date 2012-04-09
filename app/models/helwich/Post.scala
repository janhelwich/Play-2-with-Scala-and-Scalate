package models.helwich

import java.util.Date
import play.api.db._
import play.api.Play.current
import anorm.Sql._
import anorm._
import anorm.SqlParser._

case class Post(title: String,  posted: Date, content: String, author: User)

object Post{
  val parser = {
      get[String]("title") ~
      get[Date]("posted") ~
      get[String]("content")~
      get[Pk[Int]]("authorId") map {
      case title ~ posted ~ content ~ author => Post(title,  posted, content, User.findBy(author))
    }
  }

  def findAll() = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from posts").as(parser *)
    }
  }

  def create(post: Post): Unit = {
    DB.withConnection {
      implicit connection =>
        SQL("insert into posts(title, posted, content, authorId) values ({title}, {posted}, {content}, {authorId})").on(
          'title -> post.title,
          'posted -> post.posted,
          'content -> post.content,
          'authorId -> post.author.id
        ).executeUpdate()
    }
  }


}