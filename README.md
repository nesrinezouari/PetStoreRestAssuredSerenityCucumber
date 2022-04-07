# PetStoreRestAssuredSerenityCucumber

This is Maven project setup on serenity restassured dependencies
Feature File Pet.feature contains Worklow of Get, create, update and delete a pet according to query parameter 
and path parameter for delete operation.
Project Structure 
      /main/java      // package not Related To Test execution so why is not on Test folder
                .models
                   .pet  // package contain Object Like Pet, Tag, Pets, category can be added as models on this sampla I consider Category as String
                      .Pet.java
                      .Pets.java
                      .Tag.java
                    .json  //here we have Json file for valid and invalidata
                 
                  
                 .utilities //packages contain Contains All Method needed for handling Json File, extracting Endpoint from Properties file and Error static Message
                   .applicationProperties.java
                   .ErrorMessage.java
                   
      /test
            java  
               .adidas.commun  
                  .BaseApi.java        //Commun Method
           
                .api.adidas            
                  .PetEndPoints.java   //Endpoint to handel  CRUD Method of Object Pet with verification of repons
                  .PetsEndPoints.java  //Endpoint  to handel GetPet which return set of Pet , Verification of list of Pets will be added on next version 
                 
                 Features.steps // Package of stepdefinition (should be specified bu @Glue option on CucumberRunner )
                 Features.steps.serenity // steps serenity we can consider them as controler , steps can be shared... 
                 runners //runner of application 
                 
              ressources  
                 .features   //folder for Feature file
                   .Pet/Pet.feature
                 
                 .properties  //File who handel any Environment defition , endpointurl, token ...
                    TestApi.json
                    
                    
How It Works
Runner Start with Feture File , parse Steps and look for stepf Definition on @Glue Path defined on Cucumber Runner Option.
Every Stepdefinition call Steps that is layer to talk with Endpoint and checking Result 

How To Run Projet

we set Maven Clean Verify Goal and project Run we found index.html under target/site folder
we can run project as Junit Project Also by setting JunitRunnerTest Class to CucumberTestSuite

Next Step
We can generate data even form file or from Random data by using om.github.javafaker dependency or we can Send them on Feture File which not recommanded for visibility purpose
