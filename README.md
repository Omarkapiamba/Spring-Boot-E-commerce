# E-Commerce-Application

- This application is made with Java and Spring Boot and has built-in security. It uses Spring Data JPA to connect to a database by choice, making it easy to manage and store information about users, products, and orders. Spring Security handles user login, ensuring the REST APIs are safe and reliable.


# Features
## Admin
- **Login**
- **Users**
- **Address**
- **Categories**
- **Products**
- **Price**
- **Orders**

## User
- **Registration & Login**
- **Categories and Products**
- **Address**
- **Product Quantity**
- **Ordering Products**

## OrderController

- **Get Order by ID**
- **Get All Orders**
- **Get Orders by Email**
- **Get Orders by Date**
- **Get Orders by Product Category**
- **Create Order**
- **Update Order**
- **Delete Order**

## ProductController

- **Get Product by ID**
- **Get All Products**
- **Get Products by Category**
- **Get Available Products**
- **Create Product**
- **Update Product**
- **Delete Product**

## UserController

- **Get User by ID**
- **Get All Users**
- **Create User**
- **Update User**
- **Delete User**

## SecurityConfig

- **AuthenticationController**
- **UserDetailsService**
- **JwtAuthenticationFilter**
- **SecurityFilterChain**
- **PasswordEncoder**
- **AuthenticationManager**

The security configuration ensures:

- Open access to login and registration endpoints.
- Restricted access to order, product, and user endpoints based on user roles (USER, ADMIN).
- Stateless session management with JWT authentication.

## Security
- The API is secured using JSON Web Tokens (JWT) handled by Spring Security. To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorize option.

  ### Example:
    - Authorization: <your_jwt>

## Technologies:
- Java 17 or above
- Spring Boot 3.0
- Maven
- MySQL/PostGreSQL/H
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)

## Running the app
1. Clone the repository: git clone https://github.com/Omarkapiamba/Spring-Boot-E-commerce.git
2. The H2 database is preconfigured. If you want to use PostGreSQL, MySQL or run the app with Docker container, the configuration can be found in the file "database-alternatives.properties". Don't forget to comment out the file "data.sql" if you are using another database than H2.
3. Run the app.
4. Use the endpoint "/register" to add an account and "/login" to authenticate with tokens.
5. Explore the app's functionality by using various API endpoints to manage users, products, orders, and more.



# Thank You