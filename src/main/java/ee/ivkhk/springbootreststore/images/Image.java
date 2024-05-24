package ee.ivkhk.springbootreststore.images;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String pathToOriginal;
    private String pathToSmall;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPathToOriginal() {
        return pathToOriginal;
    }

    public void setPathToOriginal(String pathToOriginal) {
        this.pathToOriginal = pathToOriginal;
    }

    public String getPathToSmall() {
        return pathToSmall;
    }

    public void setPathToSmall(String pathToSmall) {
        this.pathToSmall = pathToSmall;
    }
}
