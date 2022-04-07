package features.steps.serenity;

import api.adidas.PetEndPoints;
import api.adidas.PetsEndPoints;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class PetSteps {
    String env;

    PetEndPoints petEndPoints ;

    PetsEndPoints petsEndPoints ;
    private Response petsResponse;
    private Integer petsResponseStatusCode = 0;
    private Response petResponse;
    private Integer petResponseStatusCode = 0;
    private Long Id;

    public PetSteps() {

	}

    public PetSteps(String env) {
    	petEndPoints= new PetEndPoints(env);
    	petsEndPoints= new PetsEndPoints(env);
    	this.env=env;
	}

    public Response getPetResponse() {
        return petResponse;
    } 
    public Response getPetsResponse() {
        return petsResponse;
    }
    public void setPetsResponse(Response petResponse) {
        this.petsResponse = petResponse;
    }

    public void setPetResponse(Response petResponse) {
        this.petResponse = petResponse;
    }

    public Integer getPetResponseStatusCode() {
        return petResponseStatusCode;
    }

    public void setPetResponseStatusCode(Integer petResponseStatusCode) {
        this.petResponseStatusCode = petResponseStatusCode;
    }

    @Step
    public boolean verifyBodyResponsePet(String Method) {
    	
        return petEndPoints.verifyResponseValuesAreAsExpected(getPetResponse(),"Pet",Method);
    }
 



	public Integer getPetsResponseStatusCode() {
		return petsResponseStatusCode;
	}

	public void setPetsResponseStatusCode(Integer petsResponseStatusCode) {
		this.petsResponseStatusCode = petsResponseStatusCode;
	}

	public void GetPetsRequest(String key, String value,String getpath) {
		petsEndPoints.getpetsbyparam(env, key, value,getpath);
		 setPetsResponse(petsEndPoints.getPetsResponse());
	     setPetsResponseStatusCode(petsEndPoints.getPetsResponseStatusCode());
		
	}
	 @Step
	    public void PostPetRequest( String filename) {
	        petEndPoints.postPet(env, filename);
	        setPetResponse(petEndPoints.getPetResponse());
	     setId((Long)petEndPoints.getElementFromResponse(petEndPoints.getPetResponse(), "id"));
	        setPetResponseStatusCode(petEndPoints.getPetResponseStatusCode());
	    }
	 @Step
	public void PutPetRequest(String env, String filename,String param, String newvalue) {
	petEndPoints.putPet(env, filename, param, newvalue);
	setPetResponse(petEndPoints.getPetResponse());
    setPetResponseStatusCode(petEndPoints.getPetResponseStatusCode());
	}

	public void DeletePetRequest(String path_param) {
		// if we don't specify id number so we will will delete Pet Created by Script on previous steps
		if (path_param.equals("id="))
		{
		path_param=path_param.concat(Id.toString());
		}
		petEndPoints.DeletePet( path_param);
		setPetResponse(petEndPoints.getPetResponse());
	    setPetResponseStatusCode(petEndPoints.getPetResponseStatusCode());
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	 
	

 
  }

