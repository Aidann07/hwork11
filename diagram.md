```mermaid
@startuml

title Компонент диаграммасы: Orders жүйесі

package "Frontend" {
  [Client Web App]
  [Courier Mobile App]
}

package "Backend" {
  [Orders Management]
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
[Client Web App] --> [Orders Management] : REST API
[Courier Mobile App] --> [Orders Management] : REST / WebSocket

[Orders Management] --> DB : CRUD (orders)
[Warehouse Management] --> DB : Inventory

[Orders Management] --> [Route Optimization] : route request
[Route Optimization] --> CourierAPI : location data

[Orders Management] --> [Courier Integration] : assign courier
CourierAPI --> [Courier Integration] : tracking updates

[Orders Management] --> [Notification Service] : send events
[Notification Service] --> [Client Web App] : sms/push/email

[Analytics Service] --> DB : analytics
[Orders Management] --> PaymentAPI : payments

@enduml


```
