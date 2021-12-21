package vn.edu.hcmus.hornsneaker.dao.domain;

public class CartEntry {
    String image;
    String name;
    int amount;
    int price;
    String priceFormatted;
    Long cartEntryId;

    public CartEntry(Long cartEntryId, ProductEntity product, int amount) {
        this.cartEntryId = cartEntryId;
        image = product.getImage();
        name = product.getName();
        price = product.getPrice();
        priceFormatted = product.getPriceFormatted();
        this.amount = amount;
    }

    public Long getCartEntryId() {
        return cartEntryId;
    }

    public void setCartEntryId(Long cartEntryId) {
        this.cartEntryId = cartEntryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

    public void setPriceFormatted(String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }

}
