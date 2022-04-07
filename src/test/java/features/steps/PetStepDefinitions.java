package features.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import features.steps.serenity.PetSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStepDefinitions {

	@Steps
	PetSteps petSteps;
	
	

	private String env;
	private String filename;

	@Given("User Select environment {string}")
	public void User_Select_environment(String env) {
		this.env = env;
		petSteps = new PetSteps(env);
	}

	@Given("User Get pets with Filter {string} equal to {string} for {string}")
	public void user_Get_pets_with_filter_equal_to_for(String key, String value, String getpath) {
		
		petSteps.GetPetsRequest(key, value,getpath);
	}


	@When("User receive status code {int} for API get Pet")
	public void User_receive_status_code_for_API_get_Pet(Integer code) {
		assertEquals(code, petSteps.getPetsResponseStatusCode());
	}

	@When("User Post Pet with {string}")
	public void user_Post_Prospect_with_for(String filename) {
		this.filename = filename;
		
		petSteps.PostPetRequest(filename);
        
	}
	
	@When("User receive status code {int} for API post Pet")
	public void User_receive_status_code_for_AP_Post_Pet(Integer code) {
		assertEquals(code, petSteps.getPetResponseStatusCode());
	}

	@When("Create Pet response should be valid")
	public void create_pet_response_should_be_valid() {
		assertTrue(petSteps.verifyBodyResponsePet("Post"));
	}


	@When("User receive status code {int} for API put Pet")
	public void User_receive_status_code_for_API_Put_Pet(Integer code) {
		assertEquals(code, petSteps.getPetResponseStatusCode());
	}

	
	@When("User Update Pet {string} with {string}")
	public void User_Update_Pet_with(String param, String newvalue) {
		
		petSteps.PutPetRequest(env, filename, param, newvalue);
	}
	@When("Update Pet response should be valid")
	public void update_Pet_response_should_be_valid() {
		assertTrue(petSteps.verifyBodyResponsePet("Put"));
	}

	@Then("User Delete Pet with {string}")
	public void User_Delete_Pet_with(String path_param) {
		

		petSteps.DeletePetRequest(path_param);

	}
	@When("User receive status code {int} for API delete Pet")
	public void User_receive_status_code_for_AP_Delete_Pet(Integer code) {
		assertEquals(code, petSteps.getPetResponseStatusCode());
	}
	@Then("Delete Pet response should be valid")
	public void Delete_Pet_Reponse_Should_Be_Valid() {
		assertTrue(petSteps.verifyBodyResponsePet("Delete"));
	}
}
