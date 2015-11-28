package utils.di

import java.net.URI

import com.google.inject.{AbstractModule, Provides}
import com.mongodb.DBCollection
import com.mongodb.casbah.{MongoClient, MongoDB}
import models.daos.{MongoUserDAO, UserDAO}
import net.codingwell.scalaguice.ScalaModule
import play.api.Play
import play.api.Play.current
import utils.di.annotations.UserDb

class DbModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserDAO].to[MongoUserDAO]
  }

  @Provides
  def providesMongoDb(): MongoDB = {
    val dbUri = Play.configuration.getString("db.uri").get

    val uri: URI = new URI(dbUri)

    MongoClient(uri.getHost, uri.getPort).getDB("routy")
  }

  @Provides
  @UserDb
  def userDb(db: MongoDB): DBCollection = {

    val users = "users"

    db.getCollection(users)
  }
}