package models.helwich

import java.util.Date

case class Comment(author:String, postedAt:Date , content:String, post:Post)
