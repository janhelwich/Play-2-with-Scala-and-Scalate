package controllers

import com.codahale.jerkson.Json._

import play.api.mvc._
import java.util.Date
import models.helwich.Post

object Application extends Controller {

  def index = Action {
    Ok( Scalate("main-blog.scaml").render('title -> "My kewl blogging app"))
  }
  
  def listPosts = Action {
    Ok( generate(Post.findAll())).as(JSON)
  }

  def submitBlog = Action { request =>
    val body: Option[Map[String, Seq[String]]] = request.body.asFormUrlEncoded

    body.map { map =>
      Post.create(new Post(map.get("title").get.head, new Date(), map.get("content").get.head))
      Ok("Saved")
    }.getOrElse{
      BadRequest("Expecting form url encoded body")
    }
  }

}