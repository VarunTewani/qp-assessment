# Grocery Booking App

## Description
The Grocery Booking App is a web-based application that allows users to browse, add, update, and delete grocery items, as well as place orders for selected items. This README provides instructions on how to use the application.

## Installation Instructions
1. Clone the repository: https://github.com/VarunTewani/qp-assessment/tree/master
2. Build the project locally
3. Run the application(Default port:8080)

## Usage
1. Browse the list of available grocery items.
2. Add new grocery items to the inventory.
3. Update existing grocery items (e.g., name, price, inventory).
4. Delete grocery items from the inventory.
5. Place orders for selected grocery items.

## Configuration
- The application requires a connection to a database. Configure the database connection settings in the application.properties file.

## Database
- Before running application run below command in mySQL for creating a tables in database.

CREATE TABLE `customer_order` (
   `id` int NOT NULL AUTO_INCREMENT,
   `user_id` int DEFAULT NULL,
   `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY `user_id` (`user_id`),
   CONSTRAINT `customer_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `grocery_item` (
   `id` int NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `price` decimal(10,2) DEFAULT NULL,
   `inventory` int DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `order_item` (
   `id` int NOT NULL AUTO_INCREMENT,
   `order_id` int DEFAULT NULL,
   `grocery_item_id` int DEFAULT NULL,
   `quantity` int NOT NULL,
   PRIMARY KEY (`id`),
   KEY `order_id` (`order_id`),
   KEY `grocery_item_id` (`grocery_item_id`),
   CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`id`),
   CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`grocery_item_id`) REFERENCES `grocery_item` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 CREATE TABLE `user` (
   `id` int NOT NULL AUTO_INCREMENT,
   `username` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

NOTE : Please Add user in user table 
for e.g. : Insert into user values(1,"Varun");


## API Documentation
- The application exposes RESTful API endpoints for interacting with grocery items and orders.

1.Get Grocery Item - http://localhost:8080/admin/grocery-items

2.Get Grocery Item By Id - http://localhost:8080/admin/grocery-items/{id} 

3. Add Grocery Item - POST - http://localhost:8080/admin/grocery-items

Request Body - {
  "name": "serum",
  "price": 250,
  "inventory": 20
}


4. Update Grocery Item - PUT - http://localhost:8080/admin/grocery-items/{id}

Request Body - {
  "name": "serum",
  "price": 200,
  "inventory": 35
}

5. Delete Grocery Item - Delete - http://localhost:8080/admin/grocery-items/{id}

6. Get Avaialble Grocery Item (It ll get Grocery item whose quantity` is non zero in inventory) - http://localhost:8080/user/available-grocery-items

7. Place Order - POST - http://localhost:8080/user/orderPlace

Request Body - {
  "userId": 1,
  "orderItems": [
    {
      "groceryItemId": 9,
      "quantity": 5
    },
     {
      "groceryItemId": 6,
      "quantity": 5
    }
  ]
}

Note : You can place multiple order for one user using "PLACE ORDER API"