package crud.project.demo.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;

	private Integer price_in_cents;

	private Boolean active;

	public Product() {

	}

	public Product(String id, String name, Integer price_in_cents, Boolean active) {
		this.id = id;
		this.name = name;
		this.price_in_cents = price_in_cents;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice_in_cents() {
		return price_in_cents;
	}

	public void setPrice_in_cents(Integer price_in_cents) {
		this.price_in_cents = price_in_cents;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Product(RequestProduct requestProduct) {
		this.name = requestProduct.name();
		this.price_in_cents = requestProduct.price_in_cents();
		this.active = true;
	}
}