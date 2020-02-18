package me.pabloestrada.core.personalwebsite.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public final class ProjectInfo
{
    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String type;

    @JsonProperty(required = true)
    private String description;

    @JsonProperty(required = true)
    private String funFact;

    @JsonProperty(required = true)
    private String url;

    @JsonProperty(required = true)
    private String imagePath;

    @JsonProperty(required = true)
    private int year;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(required = true)
    private ObjectId id;

    public ProjectInfo() {
    }

    public ProjectInfo(final String name, final String type, final String description, final String funFact,
                       final String url, final String imagePath, final int year) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.funFact = funFact;
        this.url = url;
        this.imagePath = imagePath;
        this.year = year;
    }

    public ProjectInfo(final String name, final String type, final String description, final String funFact,
                       final String url, final String imagePath, final int year, final String id) {
        this(name, type, description, funFact, url, imagePath, year);
        this.id = new ObjectId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", funFact='" + funFact + '\'' +
                ", url='" + url + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", year=" + year +
                ", id=" + id +
                '}';
    }
}
