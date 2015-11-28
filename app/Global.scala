package app

import com.google.inject.{Guice, Stage}
import com.mohiva.play.silhouette.api.{Logger, SecuredSettings}
import play.api.GlobalSettings
import utils.di.{DbModule, SilhouetteModule}

import scala.collection.JavaConversions._

/**
  * The global configuration.
  */
object Global extends GlobalSettings with SecuredSettings with Logger {

  /**
    * The Guice dependencies injector.
    */

  val modules = Seq(new SilhouetteModule, new DbModule)

  val injector = Guice.createInjector(Stage.PRODUCTION, modules)

  /**
    * Loads the controller classes with the Guice injector,
    * in order to be able to inject dependencies directly into the controller.
    *
    * @param controllerClass The controller class to instantiate.
    * @return The instance of the controller class.
    * @throws Exception if the controller couldn't be instantiated.
    */
  override def getControllerInstance[A](controllerClass: Class[A]) = injector.getInstance(controllerClass)
}
