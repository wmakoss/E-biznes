package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

case class Product(id: Int, name: String, price: Int)

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  val products = List(
    Product(1, "Laptop", 3500),
    Product(2, "Telefon", 1900),
    Product(3, "Zegarek", 600)
  )

  implicit val productWrites: Writes[Product] = Json.writes[Product]

  def getAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(products))
  }
}
