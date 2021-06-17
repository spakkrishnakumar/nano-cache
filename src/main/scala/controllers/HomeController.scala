package controllers

import play.api.mvc.BaseController
import javax.inject
import javax.inject._
import play.api.mvc.ControllerComponents
import redis.clients.jedis.Jedis
import redis.clients.jedis.HostAndPort

@Singleton
class HomeController @Inject() (val controllerComponents: ControllerComponents)
  extends BaseController {

  val jedis = new Jedis(new HostAndPort("localHost", 6379))

  def index() =
    Action {
      Ok(
        """
          |GET   /profile/{id}         - to get cached profile
          |POST  /profile/{id}/{value} - to set value of profile
        """.stripMargin
      )
    }

  def set(id: String, value: String) =
    Action {
      jedis.setex(id, 10L, value)
      Created
    }

  def get(id: String) =
    Action {
      Option(jedis.get(id)) match {
        case Some(value) => Ok(value)
        case None        => NotFound
      }
    }

}
