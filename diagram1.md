```mermaid
abstract class User {
  - id: Long
  - name: String
  - email: String
  - phone: String
  - address: String
  - role: String
  + register()
  + login()
  + updateData()
}

class Customer {
  - loyaltyPoints: int
  - orderHistory: List<Order>
  + addToCart(p: Product, qty: int)
  + checkout()
  + leaveReview(p: Product, r: Review)
}

class Admin {
  - logEntries: List<AdminLog>
  + createProduct(...)
  + updateProduct(...)
  + deleteProduct(...)
  + viewLogs()
}

class Product {
  - id: Long
  - name: String
  - description: String
  - price: BigDecimal
  - sku: String
  + create()
  + update()
  + delete()
}

class Category {
  - id: Long
  - name: String
  + addCategory()
  + removeCategory()
}

class Warehouse {
  - id: Long
  - location: String
  - inventory: Map<Product,Integer>
  + getStock(p: Product): int
  + reserve(p: Product, qty: int)
  + release(p: Product, qty: int)
}

class Cart {
  - id: Long
  - customer: Customer
  - items: List<CartItem>
  - promoCode: PromoCode
  + addItem(p: Product, qty: int)
  + removeItem(p: Product)
  + applyPromo(code: PromoCode)
  + calculateTotal(): BigDecimal
}

class CartItem {
  - product: Product
  - quantity: int
  - unitPrice: BigDecimal
}

class Order {
  - id: Long
  - createdAt: Date
  - status: OrderStatus
  - customer: Customer
  - items: List<OrderItem>
  - total: BigDecimal
  - payment: Payment
  - shipment: Shipment
  + placeOrder()
  + cancelOrder()
  + pay(pm: PaymentMethod)
  + refund()
}

class OrderItem {
  - product: Product
  - quantity: int
  - price: BigDecimal
}

class Payment {
  - id: Long
  - type: PaymentType
  - amount: BigDecimal
  - status: PaymentStatus
  - date: Date
  - transactionId: String
  + process()
  + refund()
}

abstract class PaymentMethod {
  + processPayment(amount: BigDecimal): Payment
  + refundPayment(txId: String): boolean
}

class CardPayment
class EWalletPayment

class Shipment {
  - id: Long
  - address: String
  - status: ShipmentStatus
  - courier: Courier
  - trackingNumber: String
  + ship()
  + track()
  + complete()
}

class Courier {
  - id: Long
  - name: String
  - phone: String
  - assignedShipments: List<Shipment>
  + assign(s: Shipment)
}

class PromoCode {
  - code: String
  - discountPercent: double
  - flatAmount: BigDecimal
  - validFrom: Date
  - validTo: Date
  + isValid(): boolean
  + apply(total: BigDecimal): BigDecimal
}

class Review {
  - id: Long
  - product: Product
  - customer: Customer
  - rating: int
  - comment: String
  - createdAt: Date
  + publish()
}

class AdminLog {
  - id: Long
  - admin: Admin
  - action: String
  - timestamp: Date
}

' --- Наследования ---
User <|-- Customer
User <|-- Admin
PaymentMethod <|-- CardPayment
PaymentMethod <|-- EWalletPayment

' --- Ассоциации и мультиплицитет ---
Customer "1" -- "0..*" Order
Order "1" -- "0..*" OrderItem
OrderItem "*" -- "1" Product
Product "*" -- "1" Category
Order "1" -- "0..1" Shipment
Shipment "1" -- "0..1" Courier
Customer "1" -- "1" Cart
Cart "1" -- "0..*" CartItem
Warehouse "1" -- "*" Product : inventory
Product "0..*" -- "0..*" Warehouse : stocked_in

```
