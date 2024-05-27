package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{Json, Writes, Reads}
import play.api.mvc._

case class Payment(id: String, productId: Int, cardNumber: String, CVC: Int, expirationDate: String, amount: Double)

@Singleton
class PaymentController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  implicit val paymentReads: Reads[Payment] = Json.reads[Payment]

  def payment() = Action { implicit request: Request[AnyContent] =>

    val content = request.body
    val jsonObject = content.asJson
    val paymentList: Option[Payment] =
      jsonObject.flatMap(
        Json.fromJson[Payment](_).asOpt
      )

    paymentList match {
      case Some(payment) =>
        println("------------------------------")
        println("Numer karty: " + payment.cardNumber)
        println("Data waznosci: " + payment.expirationDate)
        println("CVC: " + payment.CVC)
        println("Kwota: " + payment.amount)
        println("------------------------------")
        Ok(Json.toJson("Transakcja zaakceptowana"))
      case None =>
        BadRequest
    }
  }
}
