package vn.edu.hcmus.hornsneaker.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_sizes")
public class ProductSizesEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long product_id;

    @Column
	private int size;

	@Column
	private int stock;

	//#region getter and setter
    public Long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Long getProductId() {
        return product_id;
    }

    public void setProductId(Long productId) {
        this.product_id = productId;
    }

	//#endregion
		
}
