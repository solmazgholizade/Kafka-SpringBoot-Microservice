# Kafka-SpringBoot-Microservice
This project demonstrates a microservices architecture for a shopping cart system using Kafka for inter-service communication.
The system consists of two microservices: `cartService` and `PaymentService`, which communicate via Kafka using the "order-placed" event.

## Features

- **Microservices Architecture**: Clear separation of concerns.
- **Kafka-based Communication**: Event-driven communication between microservices.
- **Error Handling and Retries**: Ensures reliable message processing.
- **Sample Code**: Includes producers, consumers, serializers, and deserializers.

## Prerequisites

The project uses the following technologies and libraries:

- Java
- Spring Boot
- Spring Data
- Apache Kafka
- H2 Database
- MapStruct
- OpenAPI
- Lombok

## Getting Started

Follow these steps to get the project up and running:

1. **Clone the repository** to your local machine:
2. **Set Up Kafka and Topic**:
   Before running the project, set up Apache Kafka and create a Kafka topic by following these steps:
     - Start ZooKeeper.
     - Start a Kafka broker.
     - Create a topic named "order-placed".
          
   These steps establish ZooKeeper, start a Kafka broker, and create the "order-placed" topic for microservices communication.
3. **Create an Order**:
   To create an order and view the results in the payment service's console and payment table, perform the following steps:
   - Make an HTTP POST request to `http://localhost:8083/order/create` using the `order.http` file or `http://localhost:8083/swagger-ui/index.html`
   - In the request body, provide order details in JSON format.
4. **Verify Results**:
   - Check the payment service's console logs for payment processing messages.
   - Inspect the payment database table to ensure that payment records are correctly stored.

## Usage

Once the microservices are running, they will communicate via Kafka using the "order-placed" event.

To simulate an "order-placed" event, use the provided `OrderPlacedProducer` class in the `cartService` microservice.

Monitor the console output for payment status messages in the `PaymentService` microservice.
