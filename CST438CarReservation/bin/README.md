# Car-Reservation
## Description
The Car reservation site is a Spring Boot application designed to allow users to find and book cars given date and city paramenters. Additionally, it provides API communication to deliver car avilability, resevatin and cancelation.

## Repo Content
- `database`: in sub folder database, contains .sql file.
- `src`: Code base
- `test`: unit tests

## Deployment
Application is deplyed to Heroku and utilizes a Kitefin-level JawsDb MySQL data base

### Link to Application 
https://cst438-car-reservation-3.herokuapp.com/carrental

### Webpages:

- /carrental/carsByCity: Searches for cars by selected city and date range, appends information to cars_by_city page
- /carrental/carsByCity/reserved: Searches for a specific car to display with reservation details
- /carrental/carsByCity/details/{id}: Gets a single car object and displays details on it

### API Routes:

- GET - /carrental/api/carsByCity/{city}/{start_date}/{end_date}: obtains car lists of cars available by city on a certain date
- GET - /carrental/api/carsByCity/details/{id}: displays a single car's detail 
- GET - /carrental/api/reservations/details/{id}l: displays single reservation
- GET - /carrental/api/reservations/{email}: displays all reservations based on email given
- POST - /carrental/api/reserve: makes a car reservation
- POST - /carrental/api/cancel: cancels a car reservation

## Database
- cars
- reservations

Schema is quite simple just two tables one for cars that is populated upon creation and a reservations table with the foreign key car_id
reservations table has date fields "date_start" and "date_end" to keep track of which cars are not avialable on which dates. 
the query `"SELECT * FROM reservations WHERE (:date_start <= date_end) and (:date_end >= date_start) AND car_id = :id"` is used to get a list 
of all cars that are reserved in  between those dates. The total number of results then gets compared against the car table's 'quantity'
if the number of reservations for that date range is more then that car gets skipped from the list
