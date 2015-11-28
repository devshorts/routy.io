package models.daos

import java.util.UUID

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.LoginInfo
import com.mongodb.{DBCollection, DBObject}
import models.User
import utils.di.annotations.UserDb

import scala.concurrent.Future

class MongoUserDAO @Inject()(@UserDb db: DBCollection) extends UserDAO {

  /**
    * Finds a user by its login info.
    *
    * @param loginInfo The login info of the user to find.
    * @return The found user or None if no user for the given login info could be found.
    */
  override def find(loginInfo: LoginInfo): Future[Option[User]] = {

    Future.successful({
      val one: DBObject = db.findOne()
      to(one)
    })
  }

  /**
    * Saves a user.
    *
    * @param user The user to save.
    * @return The saved user.
    */
  override def save(user: User): Future[User] = {
    null
  }

  /**
    * Finds a user by its user ID.
    *
    * @param userID The ID of the user to find.
    * @return The found user or None if no user for the given ID could be found.
    */
  override def find(userID: UUID): Future[Option[User]] = {
    null
  }

  def to(dBObject: DBObject): Option[User] = {
    if (dBObject == null) {
      return None
    }

    Some(
      User(
        userID = dBObject.get("userId").asInstanceOf,
        firstName = getOpt(dBObject.get("firstName")),
        lastName = getOpt(dBObject.get("lastName")),
        loginInfo = null,
        fullName = getOpt(dBObject.get("fullName")),
        email = getOpt(dBObject.get("email")),
        avatarURL = getOpt(dBObject.get("avatarUrl")))
    )
  }

  def getOpt[T](anyRef: AnyRef) : Option[T] = {
    if(anyRef == null){
      None
    }
    else{
      anyRef.asInstanceOf
    }
  }
}
