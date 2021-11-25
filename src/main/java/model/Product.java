package model;

/**
 * The type Product - entity in the database: has id, name and stock.
 * @author Ariana Horvath
 */
public class Product {
    private int id;
    private String name;
    private int stock;

    /**
     * Instantiates a new Product.
     */
    public Product(){
    }

    /**
     * Instantiates a new Product.
     *
     * @param id    the id
     * @param name  the name
     * @param stock the stock
     */
    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    /**
     * Instantiates a new Product.
     *
     * @param name  the name
     * @param stock the stock
     */
    public Product(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
