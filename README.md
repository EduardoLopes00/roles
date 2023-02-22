# Explanation about the implementation
I've spent some time analysing the source code and thinking about how I would implement it. Then, I decided to try the classic MVC with clean architecture elements.
I tried to cover every class that has methods with interfaces.
I separated the folder scheme into:

  -**Data**: Store everything related to data, in this cases, models and repositorties.
  -**Infra: Store classes that are used in a global context. I opted for let the exceptions here because it makes sense for me
  -**Services**: Store the services interfaces and its implementations
  -**Web**: Store everything related to web and requests. I included the clients classes here, along with the controllers and the DTO's.

## Clients
I decided to separate specific classes to make the clients (Teams and Users), this way it was easier to handle the webClinet in a way that would allow more flexibility.
Following that, I created beans of webClients, one to each client, and then created a class to store the environment variables globally.
At the end, both classes are instantiated inside the client class. 
In this way, if necessary, would be easy to make changes in the lib responsible for the requests or in the host variables without affects classes that are consuming these clients.
I observed that when, for exeample, calling the getTeams/getUsers method, the content of the object was different of calling the getTeamById/getUserById method.
Thinking about that, for the requests that returns many data, I created a DTO with the specific fields(Id and Name) and to those which return a single data, I created a model.

## Controllers
There is not much secret in this part, I implemented the requests and created DTO to when I found necessary.

## Services
  I will talk about the approach of each method in the services. 
  
  #### Membership
  -**_assignRole_**: This one was a little tricky because I didn't know whether I should consider a membership that hasn't a role assigned. In the end I opted for do it.
  So I had to validate whether I should update or create the membership based in the data. I took a time doing this because of a bug in the update situation.
  -**_getMembershipByRole_**: I decided to not throws exceptions and return an empty list in case of doesn't exist members with the searched roleId.
  
  #### Role
  -**_createRole_**: This one I tried to keep it as simple as possible. Verify whether the role already exists. If so, throw exception, otherwise create the role normally
  -**_getRoleByUserIdAndTeamId_**: As the _createRole_, there's no special scenarios here. Verify if exists, if so, return the data, otherwise, throw exception
  -**_deleteRole_**: This one I implemented to fill the necessity of handle the deletion of roles that are linked to some membership. I created a new Exception just for this one, called _CannotDeleteException_, I though about a scenario where we could delete memberships or even another data that could be added further.
  First I make a request to verify whether this role is linked to memberships. If so, I throw the exception with a list of ID's of membership that the role is linked to.
  
## Tests

- I splited the folders in the same way as the implementation module.
- I separated the objects mock by file to make the addition and removal easier.

Even though I could implement the unit tests for services, I couldn't do it for controllers and clients. I tried several ways but nothing seems to work. As I didn't have time to keep trying, I put it aside and focused on services.
I faced throubles with the mockito when trying to mock repository.save methods. After search a lot, I ended up posting a question at Stack Overflow, where someone helped me.
You can check it [here](https://stackoverflow.com/questions/75531901/error-with-mockito-at-java-unit-test-strict-stubbing-argument-mismatch-please?noredirect=1#comment133262426_75531901)

# Running the code:

### Version: 
- SDK: 19
- Spring: 3.0.2

- Open the console and type
`mvn spring-boot:run`

# Suggestions 

### User Client:
- Create a endpoint to retrieve all teams that this client belongs with a flag isTechLead
- Create more filters, such as Team or user name

### Teams Client:
- Create a endpoint to get a teams by userId
- Create endpoint do get teams that hasn't members or hasn't tech leads.
