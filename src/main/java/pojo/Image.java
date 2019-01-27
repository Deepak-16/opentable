package pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="original")
    private String original;

    @Column(name="resized")
    private String resized;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getResized() {
        return resized;
    }

    public void setResized(String resized) {
        this.resized = resized;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
