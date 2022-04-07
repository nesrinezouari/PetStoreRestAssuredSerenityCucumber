package models.pet;

import java.util.List;
import java.util.Objects;

import org.json.JSONObject;

import utilities.ApplicationProperties;

public class Pet {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	private List<Tag> tags;
	private String name;
	private List<String> photoUrls;
	private String status;
	ApplicationProperties pros = new ApplicationProperties();

	public Pet() {

	}

	public Pet(String datajsonfilename) {
		setName((String) pros.GetDataByKeyFromJsonFile(datajsonfilename, "name"));
		setTags((List<Tag>) pros.GetDataByKeyFromJsonFile(datajsonfilename, "tags"));
		setPhotoUrls((List<String>) pros.GetDataByKeyFromJsonFile(datajsonfilename, "photoUrls"));
		setStatus((String) pros.GetDataByKeyFromJsonFile(datajsonfilename, "status"));

	}

	public Pet(Long id, String name, List<Tag> tags, List<String> phtoUrls, String status) {
		setId(id);
		setName(name);
		setTags(tags);
		setPhotoUrls(phtoUrls);
		setStatus(status);
	}

	public Pet(org.json.simple.JSONObject datajson) {
		Long id = (Long) datajson.get("id");
		setId(id);

		String status = (String) datajson.get("status");
		setStatus(status);

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pet pet = (Pet) o;
		String name = getName();
		String nameResponse = pet.name;
		String photoUrls = getPhotoUrls().get(0);
		String photoUrlsResponse = pet.photoUrls.get(0);
		String status = getStatus();
		String statusResponse = pet.status;
		boolean equals =true;
		for (int i = 0; i < getTags().size(); i++) {

			Long Tagsid = getTags().get(i).getId();
			Long Tagsidresponse = pet.getTags().get(i).getId();
			String Tagsname = pet.tags.get(i).getName();
			String Tagsnameresponse = pet.getTags().get(i).getName();
			 equals = equals && ((Objects.equals(Tagsid,Tagsidresponse))&&(Objects.equals(Tagsid,Tagsidresponse)));

		}
		Long Tagsid = getTags().get(0).getId();
		String Tagsname = pet.tags.get(0).getName();

		return Objects.equals(name, nameResponse) && Objects.equals(photoUrls, photoUrlsResponse)
				&& Objects.equals(status, statusResponse)
		 && equals
		;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags2) {
		this.tags = tags2;
	}
}
