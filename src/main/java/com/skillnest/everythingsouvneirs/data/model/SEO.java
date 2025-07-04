package com.skillnest.everythingsouvneirs.data.model;

public class SEO {
    private String slug;
    private String metaTitle;
    private String metaDescription;

    // Constructors
    public SEO() {}

    // Getters and Setters
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }

    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }
}
