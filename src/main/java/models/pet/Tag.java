package models.pet;

public class Tag {
	private Long id;
	private String name;

	
	public Tag() {
	}


	public Tag(Long idtag, String name) {
	
		this.id = idtag;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
