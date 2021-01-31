# meal_api
Api that allows you to search food recipes, make an account to save your favorite meals and send an email with it. Powers by https://www.themealdb.com

## Run

* Download or clone repository and run it in IntelliJ IDEA
* Go to  ```meal/src/main/resources/application.properties```
and in ```spring.datasource.url``` connect with your MySql database,
in ```spring.datasource.username and spring.datasource.password```
enter your username and password to database. Next in ```spring.mail.username and spring.mail.password``` enter valid
gmail email and password if you want api be able to send emails

# Register

* To make an account use client like Postman, go to:
```
http://localhost:{your_default_port}/register
```
   and send a body in POST request like example below:
```
{
    "login": "seba123",
    "email": "seba123@email.com",
    "password": "password123"
}

```
## Api map:

* You can search meal by name typing it in mealName like below (GET request):
```
http://localhost:{your_default_port}/meal/name/{mealName}
```
* Search by category (GET request):
```
http://localhost:{your_default_port}/meal/category/{mealsCategory}
```
* List all categories (GET request):
```
http://localhost:{your_default_port}/meal/category/{mealsCategory}
```
* Search by first letter (GET request): 
```
http://localhost:{your_default_port}/meal/first/{firstLetter}
```
* Search by id (GET request): 
```
http://localhost:{your_default_port}/meal/id/{mealId}
```
* Save meal to favourite (POST request):
```
http://localhost:{your_default_port}/meal/id/{mealId}
```
* Delete meal from favourite (DELETE request):
```
http://localhost:{your_default_port}/meal/id/{mealId}
```
* List your favourite meals (GET request):
```
http://localhost:{your_default_port}/meal/saved
```
* Send an email with favourite meals (GET request):
```
http://localhost:{your_default_port}/meal/send
```


