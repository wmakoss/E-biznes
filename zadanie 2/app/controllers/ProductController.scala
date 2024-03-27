package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json, OFormat}


case class Product(var id: Int, var name: String)

object Product
{
  implicit val format: OFormat[Product] = Json.format[Product]
}


@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {


  var products: List[Product] = List( new Product(1, "apple"), new Product(2, "orange"), new Product(3, "pear"))


  def getAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson[List[Product]](products: List[Product]))
  }


  def get(id: Int) = Action { implicit request: Request[AnyContent] =>
    val element = products.find(Product => Product.id == id)
    val json = Json.toJson(element)

    if (element.isEmpty) {
      NotFound("")
    } else {
      Ok(json)
    }
  }


  private def findfreeindex(): Int = {
    var i = 1
    while(products.exists(Product => Product.id == i)) {
      i = i + 1
    }
    i
  }


  def post() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent          = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody.map { json =>
    {
      val product = Product(findfreeindex(), (json \ "name").as[String])
      products = product :: products
      Ok(Json.toJson(product))
    }}.getOrElse {
      BadRequest("Expecting application/json request body")
    }
  }


  private def updateProduct(product: Product, id: Int, name: String): Product = {
    if (product.id.equals(id)) {
      return new Product(id, name)
    }
    product
  }


  def put(id: Int) = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent          = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody.map { json => {
      val product = Product(id, (json \ "name").as[String])
      products = products.map(product => updateProduct(product, id, (json \ "name").as[String]))
      Ok(Json.toJson(product))
    }}.getOrElse {
      BadRequest("Expecting application/json request body")
    }
  }


  def delete(id: Int) = Action { implicit request: Request[AnyContent] =>
    val element = products.find(Product => Product.id == id)
    val json = Json.toJson(element)

    if (element.isEmpty) {
      NotFound("")
    } else {
      products = products.filter(product => !(product.id == id))
      Ok(" ")
    }
  }


}
