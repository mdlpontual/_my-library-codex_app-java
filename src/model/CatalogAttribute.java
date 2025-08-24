package model;

public abstract class CatalogAttribute {
    protected Integer id;
    protected String name;

    public CatalogAttribute(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
