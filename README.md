# Web-Quiz-Engine
## JetBrains Academy projects ([Hyperskill.org](https://hyperskill.org))

It is an engine for creating/solving/deleting quizzes by multiple authorized (**HTTP Basic**) users. 
This project built on **REST** principles and uses **Spring Boot** framework and its embedded **H2 database** for storing quizzes and users. **JSON** is used for transmitting objects between a client and a server.

API:

**/api**\
&nbsp;&nbsp;&nbsp;&nbsp;**/quizzes:**\
&nbsp;&nbsp;&nbsp;&nbsp;POST: Create a quiz.\
&nbsp;&nbsp;&nbsp;&nbsp;GET: Get all quizzes.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**/{id}:**\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GET: Get quiz by id.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DELETE: Delete quiz by user's id.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**/solve:**\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;POST: Solve quiz with recieved quiz's id.\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**/completed:**\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GET: Get all quizzes, solved by currently authorized user.\
&nbsp;&nbsp;&nbsp;&nbsp;**/register:**\
&nbsp;&nbsp;&nbsp;&nbsp;POST: Register a user.
