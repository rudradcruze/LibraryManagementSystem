# Library Management System
This is a simple library management system that I have created using Java and MySQL. The system allows you to add, update, delete, and search for books. It also allows you to issue and return books. The system is designed to be used by librarians and library staff.

## Features
- Add books
- Update books
- Delete books
- Search by book title and id
- Add authors
- Update authors
- Delete authors
- Search by author name and id
- Assign authors to books
- Get a list of books by author
- Get a list of book published in a after some date

## Technologies
- Java
- Spring Boot
- MySQL

## Database Schema
The database schema is as follows:
- Book
  - id
  - title
  - isbn
  - published_date
  - author_id
  - quantity
  - isAvailable
  - price
  - category
  - publisher
  - language
  - pages
  - description
  - created_at
  - updated_at

- Author
  - id
  - name
  - email
  - phone
  - address

## API Endpoints
- `/books` - Get all books (`name` and `date` query parameters are optional) (_GET_)
- `/books/{id}` - Get book by id (_GET_)
- `/books` - Add a book (_POST_)
- `/books/{id}` - Update a book (_PUT_)
- `/books/{id}` - Delete a book (_DELETE_)
- `/books/{id}` - Update partial book details (_PATCH_)
- `/books/{id}/author/{authorId}` - Assign an author to a book (_POST_)
- `/books/author/{id}` - Get books by author (_GET_)
- `/books/publishedAfter/{date}` - Get books published after a date (_GET_)

- `/authors` - Get all authors (`name` query parameter is optional) (_GET_)
- `/authors/{id}` - Get author by id (_GET_)
- `/authors` - Add an author (_POST_)
- `/authors/{id}` - Update an author (_PUT_)
- `/authors/{id}` - Delete an author (_DELETE_)
- `/authors/{id}` - Update partial author details (_PATCH_)

## How to run
1. Clone the repository
2. Update the `application.properties` file with your MySQL username and password
3. Run the application