import java.math.BigDecimal;

public class Protein {

	String id;
	String imageInactive;
	String imageActive;
	String name;
	String description;
	BigDecimal price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageInactive() {
		return imageInactive;
	}

	public void setImageInactive(String imageInactive) {
		this.imageInactive = imageInactive;
	}

	public String getImageActive() {
		return imageActive;
	}

	public void setImageActive(String imageActive) {
		this.imageActive = imageActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
