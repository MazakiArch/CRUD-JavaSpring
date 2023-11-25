package crud.project.demo.controllers;

import crud.project.demo.domain.product.Product;
import crud.project.demo.domain.product.ProductRepository;
import crud.project.demo.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductControllers {
	@Autowired
	private ProductRepository repository;

	@GetMapping
	@Transactional
	public ResponseEntity getAllProduct() {
		var allProducts = repository.findAllByActiveTrue();
		return ResponseEntity.ok(allProducts);
	}

	@PostMapping
	@Transactional
	public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		repository.save(newProduct);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	@Transactional
	public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
		Optional<Product> optionalProduct = repository.findById(data.id());
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(data.name());
			product.setPrice_in_cents(data.price_in_cents());
			product.setActive(data.active());
			return ResponseEntity.ok(product);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteProduct(@PathVariable String id) {
		Optional<Product> optionalProduct = repository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setActive(false);
			return ResponseEntity.ok(product);
		} else {
			throw new EntityNotFoundException();
		}
	}

}