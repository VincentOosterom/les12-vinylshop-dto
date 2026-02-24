package nl.les12vinylshopdto.les12vinylshopdto.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createDate;
    private LocalDateTime editDate;

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.editDate = LocalDateTime.now();
    }
}
