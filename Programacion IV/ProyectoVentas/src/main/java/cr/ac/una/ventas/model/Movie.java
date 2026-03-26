/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ventas.model;

public class Movie {
    private int id;
    private String title;
    private String photoUrl;
    private int releaseYear;
    private Integer categoryId;
    private Category category;

    public Movie() {}

    public Movie(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}