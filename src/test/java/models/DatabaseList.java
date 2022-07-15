package models;

public class DatabaseList {

    String name;
    Double sizeOnDisk;
    Boolean empty;

    public DatabaseList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSizeOnDisk() {
        return sizeOnDisk;
    }

    public void setSizeOnDisk(Double sizeOnDisk) {
        this.sizeOnDisk = sizeOnDisk;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

}
