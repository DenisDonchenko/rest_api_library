<h1>Technologies</h1>
<ul>
<li>Java 16</li>
<li>Spring Boot</li>
<li>Maven</li>
<li>MySQL 8.0.13, Liquibase</li>	
</ul>
<h1>Requirements</h1>
<ul>
<li>JDK 16</li>
<li>Any IDE of your choice (Lombok plugin required). For IntelliJ IDEA: Build, Execution, Deployment -> compiler -> Annotation processors -> Enable annotation processing</li>
<li>MySQL 8.0.13 or latest</li>
</ul>
<h1>Explore Rest APIs</h1>
The app defines following CRUD APIs.
<h3>Books</h3>

<table>
 <thead>
  <tr>
	<th>Method</th>
	<th>Url</th>
	<th>Decription</th>
	<th>Sample Valid Request Body</th>
  </tr>
 </thead>
<tbody>
	<tr>
	  <td>GET</td>
	  <td>/books/all</td>
	  <td>Get a list of book</td>
	  <td><a >JSON</a></td>
	</tr>
	<tr>
    <tr>
	  <td>GET</td>
	  <td>/books/free</td>
	  <td>Get a list free books</td>
	  <td><a>JSON</a></td>
	</tr>
	<tr>
	  <td>GET</td>
	  <td>/books/{id}</td>
	  <td>Get books by id</td>
	  <td><a >JSON</a></td>
	</tr>
	<tr>
	  <td>POST</td>
	  <td>/books/add</td>
	  <td>Adding a book</td>
	  <td><a>JSON</a></td>
	</tr>
    <tr>
	  <td>PUT</td>
	  <td>/books/{id}</td>
	  <td>Update book by id</td>
	  <td><a>JSON</a></td>
	</tr>	
    <tr>
	  <td>DELETE</td>
	  <td>/books/{id}</td>
	  <td>Delete book by id</td>
	  <td><a >JSON</a></td>
	</tr>
	

</tbody>
</table>
<h3>Users</h3>

<table>
 <thead>
  <tr>
	<th>Method</th>
	<th>Url</th>
	<th>Decription</th>
	<th>Sample Valid Request Body</th>
  </tr>
 </thead>
<tbody>
	<tr>
	  <td>GET</td>
	  <td>/users/all</td>
	  <td>Get a list of user</td>
	  <td><a >JSON</a></td>
	</tr>
	<tr>
	  <td>GET</td>
	  <td>/users/{id}</td>
	  <td>Get user by id</td>
	  <td><a >JSON</a></td>
	</tr>
	<tr>
	  <td>POST</td>
	  <td>/users/add/</td>
	  <td>Adding a user</td>
	  <td><a>JSON</a></td>
	</tr>
	<tr>
	  <td>PUT</td>
	  <td>/users/{id}</td>
	  <td>Update user by id</td>
	  <td><a >JSON</a></td>
	</tr>
    <tr>
	  <td>DELETE</td>
	  <td>/users/{id}</td>
	  <td>Delete user by id</td>
	  <td><a>JSON</a></td>
	</tr>
</tbody>
</table>

<h3>Booking</h3>

<table>
 <thead>
  <tr>
	<th>Method</th>
	<th>Url</th>
	<th>Decription</th>
	<th>Sample Valid Request Body</th>
  </tr>
 </thead>
<tbody>
	<tr>
	  <td>POST</td>
	  <td>/booking/take/{id_book}/{id_user}</td>
	  <td>Take book</td>
	  <td><a >JSON</a></td>
	</tr>
    <tr>
	  <td>POST</td>
	  <td>/booking/returned/{id_book}</td>
	  <td>Returned book</td>
	  <td><a >JSON</a></td>
	</tr>
</tbody>
</table>