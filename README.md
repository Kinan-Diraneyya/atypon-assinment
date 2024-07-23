# Atypon Assignment
This application is a submission for an online assignment for Atypon for the position of full stack developer.
It has a backend made using **Spring Boot** and a frontend made using **React JS**.

## Running the Application
The easiest way to run the application is using docker. It involves the following steps
1. Clone the application from [this repo](https://github.com/Kinan-Diraneyya/atypon-assinment/)
2. Make sure that docker is running
3. Navigate to the project's root
4. Set up your API key and Spoonacular URL environment variables: `export SPOONACULAR_BASE_URL=https://api.spoonacular.com` & `export SPOONACULAR_API_KEY=<You API key>`. Please note that this command may differ depending on your shell (`set` for windows, `$env` for powershell)
5. Run `docker-compose up --build`
6. Access the application on `localhost:3000`

Alternatively, you can build and run each application individually, which is done through the following steps:
1. Clone the application from [this repo](https://github.com/Kinan-Diraneyya/atypon-assinment/)
2. Rename `application-template.yml` to `application.yml`. Then, add your API key to the file
3. Run the Java project by navigating to its folder and running `./mvnw package && java -jar target/atypon.assignment-0.0.1-SNAPSHOT.jar`
4. Run the React project by navigating to its folder and running `npm i`, followed by `npm run build`, followed by `npm run start`
5. Access the application on `localhost:3000`

## Basic Usage
You can either use the application's frontend by navigating to `localhost:3000`, its swagger UI by navigating to `localhost:8080/swagger-ui/index.html`, or directly call one of its 3 endponts:
1. `localhost:8080/search?query=<some query>` Returns a list of recipes based on the specified query. For example: `localhost:8080/search?query=pasta`
2. `localhost:8080/<Some recipe ID>/information` Returns the info of a recipe. For example: `localhost:8080/642583/information`
3. `localhost:8080/<Some recipe ID>/customized-calories?exclude=<some ingredient>` Returns the calories of a recipe, minus the calories of any excluded ingredient. For example: `localhost:8080/642583/customized-calories?exclude=butter&exclude=ham`

## Approach and Choices
- **Security**: This application does not provide any degree of auth. This is due to the combination of the API's simplicity (especially all of them being read-only) and auth not being specified in the requirements. However, I would normally use JWT with role-based auth for such applications.
- **Request Validation**: The request validation approach is internationally simplistic, as most APIs need a single required param, but bean validation can be used for more complex use cases.
- **Utility functions locality**: To avoid cluttering the project, all utility methods that are used only once in the project and defined where they are used. In a larger project, utility classes would be used instead.
- **Use of generative AI**: It is important to note that I make extensive use of generative AI. Though I wrote a lot of the code/documentation myself (including this file), generative AI was a huge aid.

## Notes on Requirements
In a real-life scenario, I would have discussed the requirements before proceeding, but that is not often the approach with online assignments, so I improvised the following regarding the below requirements for recipe information
>The total number of calories **must** be calculated by adding the calories of each ingredient that forms the recipe

I adheared to the above requirement, even though Spoonacular API provides the total calories as a field of its standard response. The extra code/calculation was not really needed, but the requirement was met.
>The recipe name should be one of the input parameters

I used the **ID**. Recipes have **titles**, not **names**, and they are not guaranteed to be unique. I could have searched using the title and picked the first result, but that did not seem appropriate.

## Lessons and Challenges
I care to make things in the best way possible, but this is not a luxury I often get to have due to deadlines. In fact, this delivery itself is 2 days late due to how much time I invested learning the best ways to create, test, and, document this application.
This was my first time writing unit tests for a frontend application. It is worth noting that testing NextJS server components proved a big challenge, so they are the only components left untested. Additionally, I tried (and failed to do so in time) to write full integration tests for the backend.
Besides the above, I had previous experience with most of the process of creating projects like this one.

Thank you for reading this far and my regards.