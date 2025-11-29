```mermaid
@startuml

title Система управления логистикой и доставкой товаров

package "Frontend" {
  [Client Web App]
  [Courier Mobile App]
}

package "Backend" {
  [Order Management]
  [Warehouse Management]
  [Route Optimization]
  [Courier Integration]
  [Notification Service]
  [Analytics Service]
  [Auth Security]
}

database "Main DB" as DB
actor "External Courier API" as CourierAPI
actor "Payment Gateway" as PaymentAPI

' Connections
[Client Web App] --> [Order Management] : REST API
[Courier Mobile App] --> [Order Management] : REST / WebSocket

[Order Management] --> DB : CRUD operations
[Warehouse Management] --> DB : Inventory data

[Order Management] --> [Route Optimization] : route request
[Route Optimization] --> CourierAPI : location/traffic data

[Order Management] --> [Courier Integration] : assign courier
CourierAPI --> [Courier Integration] : tracking status

[Order Management] --> [Notification Service] : send status update
[Notification Service] --> [Client Web App] : push/sms/email

[Analytics Service] --> DB : analytics queries
[Order Management] --> PaymentAPI : payment requests

@enduml

```
