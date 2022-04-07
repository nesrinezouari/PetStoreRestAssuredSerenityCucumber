package api.adidas;



import io.restassured.response.Response;
import adidas.communs.BaseAPI;
import models.pet.Pet;
import models.pet.Pets;



public class PetsEndPoints extends BaseAPI{
private Pets pets;


private String errorMessage;
    private Response petsResponse;
    public Response getPetsResponse() {
		return petsResponse;
	}



	public void setPetsResponse(Response petsResponse) {
		this.petsResponse = petsResponse;
	}



	private Integer petsResponseStatusCode;
    private Integer NumberOfpets; 
    
    public PetsEndPoints(String env) {
		super(env);
	
	}


	
	public void getpetsbyparam(String env, String key, String value, String getpath) {
		getpath=PetEndPoints.PET_ENDPOINT_PATH.concat(getpath);
		if (this.petsResponse == null) {
			petsResponse =  getApiResponseWithQueryParams(getpath, key, value);
		}
	     
		
		
		int size = getResponseSize(petsResponse);
		setNumberOfpets(size);
	 
		int petsResponseStatusCode = getResponseStatus(petsResponse);
		setPetsResponseStatusCode(petsResponseStatusCode);
		
	
}

	


	public Response getpetbyparamResponse(String path,String Key, String value) {
		Response response = getApiResponseWithQueryParams(path, Key, value);
		return response;
	}


	public Integer getPetsResponseStatusCode() {
		return petsResponseStatusCode;
	}



	public void setPetsResponseStatusCode(Integer petsResponseStatusCode) {
		this.petsResponseStatusCode = petsResponseStatusCode;
	}



	public Integer getNumberOfpets() {
		return NumberOfpets;
	}



	public void setNumberOfpets(Integer numberOfPets) {
		NumberOfpets = numberOfPets;
	}



	public Pets getPets() {
		return pets;
	}



	public void setPets(Pets pets) {
		this.pets = pets;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
