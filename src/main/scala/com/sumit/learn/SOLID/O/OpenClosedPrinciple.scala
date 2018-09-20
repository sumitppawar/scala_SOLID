package com.sumit.learn.SOLID.O

object OpenClosedPrinciple {

  def main(args: Array[String]): Unit = {
    val items = List(Item("MACBOOKAIR", 60000, "DIWALI_OFFER_20RS_OFF"), Item("MACBOOKPRO", 90000, "DIWALI_OFFER_20RS_OFF"))
    val cart = Cart(items)

    println(NotOCPShoppingCartService.getTotalAmount(cart))
    println(OCPShoppingCartService.getTotalAmount(cart, new PriceCalculator))
  }
}
case class Item(name: String, price: Double, offer: String)
case class Cart(items: List[Item])

object NotOCPShoppingCartService {
  /*
  BAD EXAMPLE
  OCP not followed ShoppingCartService
  Why ?
  1.  Item added some other offer then we need to change getTotalAmount every time.
   */
  def  getTotalAmount(cart: Cart): Double =
    cart.items. foldLeft(0d) { (acc, item) =>
      item.offer match {
        case "DIWALI_OFFER_20RS_OFF" => acc + (if(item.price > 100) item.price - 20  else item.price)
        case "DASRA_OFFER_30RS_OFF" => acc + (if(item.price > 100) item.price - 30  else item.price)
        case _ =>   item.price
      }
    }
}

object OCPShoppingCartService {
  /*
  GOOD EXAMPLE
  OCP followed ShoppingCartService
   */
  def  getTotalAmount(cart: Cart, priceCalculator: PriceCalculator): Double =
    cart.items. foldLeft(0d) { (acc, item) => acc + priceCalculator.calculate(item)
    }
}

class PriceCalculator {
  def calculate(item: Item): Double =
    item.offer match {
      case "DIWALI_OFFER_20RS_OFF" => if(item.price > 100) item.price - 20  else item.price
      case "DASRA_OFFER_30RS_OFF" => if(item.price > 100) item.price - 30  else item.price
      case _ =>   item.price
    }
}






