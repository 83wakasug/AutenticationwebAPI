# ApiWebService
This program is an *inventory management system* that allows for the management of product names, names, and quantities. To use the system, **user registration** is required. Users with the role *USER* can:

- Add products
- View product information

Users with the role *ADMIN*, in addition to the above, have the capability for:

- Information updates
- Product deletion

Upon logging in or signing in, users generate a *JWT token*, which they can then use to access the program.



### Java version:
- openJdk-20

### Database
-MySQL version 8.0.34


## How to Start with MySQL and Spring Boot

### Step 1: Install MySQL

- Download and install MySQL from the [official website](https://dev.mysql.com/downloads/).

### Step 2: Configure MySQL in your Spring Boot Application

1. Open your Spring Boot application's `application.properties` or `application.yml` file.

2. Replace `your_username` and `your_password` with your MySQL username and password.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/molndal
   spring.datasource.username=your_username
   spring.datasource.password=your_password

3. Make Table molndal on MySQL
- Ensure that you have created the molndal table in your MySQL database.
4. Start AuthenticationWebAPI

Start your Spring Boot application (Application).

---

## How to Use API

### Index

1. [Signup User with User Role](#signup-user-with-user-role)
2. [Signup User with Admin Role](#signup-user-with-admin-role)
3. [Signin with Username and Password](#signin-with-username-and-password)
4. [Fetch All Data](#fetch-all-data)
5. [Fetch Data by Product Name](#fetch-data-by-product-name)
6. [Fetch Data by ID](#fetch-data-by-id)
7. [Add Products by JSON Format](#add-products-by-json-format)
8. [Update Data (only by user with admin role)](#update-data-only-by-user-with-admin-role)
9. [Delete Data (only by user with admin role)](#delete-data-only-by-user-with-admin-role)

### Signup User with User Role

- **URL:** `localhost:8080/api/v1/auth/signup`
- **Method:** POST
- **Postman Settings:**
    - Authorization: No Auth
    - Body Type: JSON
- **Data Example:**
  ```json
  {
    "firstName": "name",
    "lastName": "last",
    "email": "name@gmail.com",
    "password": "password"
  }
  ```
    - Return Token:

#### Signup User with Admin Role

- **URL:** `localhost:8080/api/v1/auth/signupAdmin`
- **Method:** POST
- **Postman Settings:**
    - Authorization: No Auth
    - Body Type: JSON
- **Data Example:**
  ```json
  {
    "firstName": "Admin",
    "lastName": "last",
    "email": "admin@gmail.com",
    "password": "password"
  }
  ```
    - Return Token:

#### Signin with Username and Password

- **URL:** `localhost:8080/api/v1/auth/signingIn`
- **Method:** POST
- **Postman Settings:**
    - Authorization: No Auth
    - Body Type: JSON
- **Data:**
  ```json
  {
    "email": "name@gmail.com",
    "password": "password"
  }
  ```
    - Return:
        - Success: Token
        - Fail: 403 error

#### Fetch All Data

- **URL:** `localhost:8080/api/v1/`
- **Method:** GET
- **Postman Settings:**
    - Authorization:
        - Type: Bearer
        - Token: Copy-paste token obtained during sign-in or login


#### Fetch Data by ID

- **URL:** `localhost:8080/api/v1/{id}`
- **Method:** GET
- **Postman Settings:**
    - Authorization:
        - Type: Bearer
        - Token: Copy-paste token obtained during sign-in or login

#### Add Products by JSON Format

- **URL:** `localhost:8080/api/v1/`
- **Method:** POST
- **Postman Settings:**
    - Authorization:
        - Type: Bearer
        - Token: Copy-paste token obtained during sign-in or login
- **Data:**
  ```json
  {
  "productName": "Orange",
    "price": 100.0,
    "stock": 500
  }
  ```

#### Update Data (only by user with admin role)

- **URL:** `localhost:8080/api/v1/{id}`
- **Method:** PUT
- **Postman Settings:**
    - Authorization:
        - Type: Bearer
        - Token: Copy-paste token obtained during sign-in or login
- **Sample Code:**
  ```json
  {
    "productName": "Orange3",
    "price": 200.0,
    "stock": 2000
  }
  ```

#### Delete Data (only by user with admin role)

- **URL:** `localhost:8080/api/v1/{id}`
- **Method:** DELETE
- **Postman Settings:**
    - Authorization:
        - Type: Bearer
        - Token: Copy-paste token obtained during sign-in or login


gitHub:https://github.com/83wakasug/AutenticationwebAPI.git


Här finns det första version av program https://github.com/83wakasug/ApiWebService.git