package api.adidas;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import adidas.communs.BaseAPI;
import io.restassured.response.Response;

import models.pet.Pet;
import models.pet.Tag;
import utilities.ErrorMessage;

public class PetEndPoints extends BaseAPI {
	static String PET_ENDPOINT_PATH = "/pet";
	private Pet pet;

	private Response petResponse;
	private Integer petResponseStatusCode;
	private Long Idpet;

	public Long getIdpet() {
		return Idpet;
	}

	public void setIdpet(Long idpet) {
		Idpet = idpet;
	}

	private String errorMessage;

	static String proj = "Api";

	public PetEndPoints(String env) {
		super(env);
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Response getPetResponse() {
		return petResponse;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/************************************************************************************************************/
	/***************************************
	 * Check Result Schema
	 *********************************************/
	/**
	 * 
	 * @param response
	 * @param Class
	 * @return
	 */
	public boolean verifyResponseValuesAreAsExpected(Response response, String Class, String Method) {
		Boolean isequal = false;

		switch (Class) {
		case "Pet": {

			if ((getPetResponseStatusCode() == 200)) {
				if (Method.equals("Delete")) {

					isequal = getErrorMessage().contains(getIdpet().toString());
				} else {
					response.getBody().prettyPrint();
					isequal = getPet().equals(response.getBody().as(Pet.class));

				}
			} else if (getPetResponseStatusCode() == 400) {
				if (getErrorMessage().equals(ErrorMessage.Pet_400_Msg)) {
					isequal = true;
				}

			} else if (getPetResponseStatusCode() == 401) {
				if (getErrorMessage().equals(ErrorMessage.Pet_404_Msg)) {
					isequal = true;
				}

			} else if (getPetResponseStatusCode() == 405) {
				if (getErrorMessage().equals(ErrorMessage.Pet_405_Msg)) {
					isequal = true;
				}

				else if (getPetResponseStatusCode() == 500) {
					isequal = getErrorMessage().contains(ErrorMessage.Pet_500_Msg.toString());

				}
			}
		}

			break;

		default:
			break;
		}
		return isequal;
	}

	/**********************************************************************************************/
	/**************************
	 * CreatePet
	 *******************************************************/
	/**********************************************************************************************/

	public void postPet(String env, String filename) {

		if (getPetResponse() == null) {
			petResponse = postPetResponse(env, filename);

			setPetResponse(petResponse);
			System.out.print(" im response for create new Pet" + getPetResponse().getBody().prettyPrint());
		}

		int statusCode = getResponseStatus(petResponse);
		setPetResponseStatusCode(statusCode);

		switch (statusCode) {
		case 200:

			SetPetObjectfromResponse(petResponse);
			break;
		case 405:
			setErrorMessage(getResponseDescription(petResponse));
		default:
			break;
		}

	}

	public Response postPetResponse(String env, String filename) {

		Response response = postApiResponse(PET_ENDPOINT_PATH, filename);
		response.getBody().prettyPrint();

		return response;

	}

	/**********************************************************************************************/
	/**************************
	 * UpdatePet
	 *******************************************************/
	/**********************************************************************************************/

	public void putPet(String env, String filename, String param, String newvalue) {

		if (getPetResponse() == null) {
			petResponse = postPetResponse(env, filename);
			setPetResponse(petResponse);
		}

		JSONObject newPetObj = UpdateDatafromJsonFile(filename, param, newvalue);

		UpdateDatafromJsonObject(newPetObj, "id", getIdpet().toString());
		Response petResponse = putPetResponse(env, PET_ENDPOINT_PATH, newPetObj.toString());
		setPetResponse(petResponse);
		int statusCode = getResponseStatus(petResponse);
		setPetResponseStatusCode(statusCode);

		if (getPetResponseStatusCode() == 200) {

			SetPetObjectfromResponse(petResponse);

		} else if (getPetResponseStatusCode() == 400) {
			setErrorMessage(getResponseDescription(petResponse));
		} else if (getPetResponseStatusCode() == 404) {
			setErrorMessage(getResponseDescription(petResponse));
		} else if (getPetResponseStatusCode() == 405) {
			setErrorMessage(getResponseDescription(petResponse));
		}

	}

	public Response putPetResponse(String env, String path, String jsonobj) {

		Response response = putApiResponse(path, jsonobj);
		response.getBody().prettyPrint();

		return response;

	}

	/**********************************************************************************************/
	/***********************************
	 * delete Pet
	 ***************************************************/
	/**********************************************************************************************/

	public void DeletePet(String path_param) {

		Response petResponse = deletePetResponse(path_param);
		setPetResponse(petResponse);
		int statusCode = getResponseStatus(petResponse);
		setPetResponseStatusCode(statusCode);
		if (getPetResponseStatusCode() == 200) {
			setErrorMessage(getResponseDescription(petResponse));

		} else if (getPetResponseStatusCode() == 400) {
			setErrorMessage(getResponseDescription(petResponse));
		} else if (getPetResponseStatusCode() == 404) {
			setErrorMessage(getResponseDescription(petResponse));
		}

	}

	private Response deletePetResponse(String path_param) {
		Response response = deleteApiResponseWithPathParams(path_param, PET_ENDPOINT_PATH);
		response.getBody().prettyPrint();

		return response;
	}

	/*********************************************************************************************/
	/***********************************
	 * set Pet comparison from Response
	 ************************/
	/*********************************************************************************************/
	/*
	 * @param petResponse
	 */
	public void SetPetObjectfromResponse(Response petResponse) {
		Long id = (Long) getElementFromResponse(petResponse, "id");
		setIdpet(id);
		String name = (String) getElementFromResponse(petResponse, "name");
		JSONArray tagsjsonarray = (JSONArray) getElementFromResponse(petResponse, "tags");
		List<Tag> tags = new ArrayList<Tag>();
		System.out.println(tagsjsonarray);
		JSONObject tagJSONObject = new JSONObject();
		for (int i = 0; i < tagsjsonarray.size(); i++) {
			tagJSONObject = (JSONObject) tagsjsonarray.get(i);
			String nametag = (String) tagJSONObject.get("name");	
			Long idtag = (Long) tagJSONObject.get("id");
			tags.add(new Tag(idtag, nametag));
		}
		List<String> photourls = (List<String>) getElementFromResponse(petResponse, "photoUrls");
		String status = (String) getElementFromResponse(petResponse, "status");
		setPet(new Pet(id, name, tags, photourls, status));
	}

}
