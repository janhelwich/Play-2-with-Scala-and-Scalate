package models.helwich

import play.api.db._
import play.api.Play.current
import anorm.Sql._
import anorm._
import anorm.SqlParser._

case class User(email: String, password: String, fullname: String, isAdmin: Boolean = false, id:Pk[Int] = NotAssigned)

object User  {
  val parser = {
      get[Pk[Int]]("id") ~
      get[String]("email") ~
      get[String]("password")~
      get[Option[String]]("fullname")~
      get[Boolean]("isAdmin") map {
      case pk ~ mail ~ name ~ fullname ~ isAdmin => User(mail, name, fullname.getOrElse(null), isAdmin, pk)
    }
  }


  def findBy(id: Pk[Int]): User = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from myuser where id = {id}").on("id" -> id.get).using(parser).single()
    }
  }


  def findAll(): Seq[User] = {
    DB.withConnection {
      implicit connection =>
        SQL("select * from myuser").as(User.parser *)
    }
  }

  def create(user: User): User = {
    DB.withConnection {
      implicit connection =>
        SQL("insert into myuser(email, password, fullname, isadmin) values ({email}, {password}, {fullname}, {isAdmin});").on(
          'email -> user.email,
          'password -> user.password,
          'fullname -> user.fullname,
          'isAdmin -> user.isAdmin
        ).executeUpdate()
        val id = SQL("SELECT SCOPE_IDENTITY()")().collect {
          case Row(id: Int) => id
        }.head
        return User(user.email, user.password, user.fullname, user.isAdmin, new Id(id))
    }
  }
}