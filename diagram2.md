%% Компонент диаграммасы: Orders жүйесі
graph TD
    %% Frontend пакеті
    subgraph Frontend
        ClientWeb[Client Web App]
        CourierMobile[Courier Mobile App]
    end

    %% Backend пакеті
    subgraph Backend
        OrdersManagement[Orders Management]
        WarehouseManagement[Warehouse Management]
        RouteOptimization[Route Optimization]
        CourierIntegration[Courier Integration]
        NotificationService[Notification Service]
        AnalyticsService[Analytics Service]
        AuthSecurity[Auth Security]
    end

    %% Database
    DB[Main DB]

    %% External actors
    CourierAPI["External Courier API"]
    PaymentAPI["Payment Gateway"]

    %% Connections
    ClientWeb -->|REST API| OrdersManagement
    CourierMobile -->|REST / WebSocket| OrdersManagement

    OrdersManagement -->|CRUD (orders)| DB
    WarehouseManagement -->|Inventory| DB

    OrdersManagement -->|route request| RouteOptimization
    RouteOptimization -->|location data| CourierAPI

    OrdersManagement -->|assign courier| CourierIntegration
    CourierAPI -->|tracking updates| CourierIntegration

    OrdersManagement -->|send events| NotificationService
    NotificationService -->|sms/push/email| ClientWeb

    AnalyticsService -->|analytics| DB
    OrdersManagement -->|payments| PaymentAPI
