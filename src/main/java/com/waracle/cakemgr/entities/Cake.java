package com.waracle.cakemgr.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Cake", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "TITLE")})
public class Cake {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long cakeId;

    @Column(name = "TITLE", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
    private String description;

    @Column(name = "IMAGE", unique = false, nullable = false, length = 300)
    private String image;

    public Cake() {}

    Cake(final String title, final String description, final String image) {

        this.description = description;
        this.image = image;
        this.title = title;
    }

    public Long getCakeId() {
        return this.cakeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cake{" + "id=" + this.cakeId + ", title='" + this.title + + '\'' + ", description='" + this.description + '\'' + ", email='" + this.title + '\'' + '}';
    }
}
