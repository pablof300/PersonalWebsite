package me.pabloestrada.core.personalwebsite.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import me.pabloestrada.core.personalwebsite.ObjectIdSerializer;
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
    private String firstImagePath;

    @JsonProperty(required = true)
    private String secondImagePath;

    @JsonProperty(required = true)
    private int year;

    @JsonProperty(required = true)
    private int priority;

    @JsonProperty(required = true)
    @JsonSerialize(using = ObjectIdSerializer.class)
    @ApiModelProperty(dataType = "string", required = true)
    private ObjectId id;

    public ProjectInfo() {
    }

    public ProjectInfo(final String name, final String type, final String description, final String funFact,  final String url,
                       final String firstImagePath, final String secondImagePath, final int year, final int priority) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.funFact = funFact;
        this.url = url;
        this.firstImagePath = firstImagePath;
        this.secondImagePath = secondImagePath;
        this.priority = priority;
        this.year = year;
    }

    public ProjectInfo(final String name, final String type, final String description, final String funFact, final String url,
                       final String firstImagePath, final String secondImagePath, final int year, final int priority, final String id) {
        this(name, type, description, funFact, url, firstImagePath, secondImagePath, year, priority);
        this.id = new ObjectId(id);
    }

    public ProjectInfo(final String name, final String type, final String description, final String funFact, final String url,
                       final String firstImagePath, final String secondImagePath, final int year, final int priority, final ObjectId id) {
        this(name, type, description, funFact, url, firstImagePath, secondImagePath, year, priority);
        this.id = id;
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

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public String getSecondImagePath() {
        return secondImagePath;
    }

    public void setSecondImagePath(String secondImagePath) {
        this.secondImagePath = secondImagePath;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", funFact='" + funFact + '\'' +
                ", url='" + url + '\'' +
                ", firstImagePath='" + firstImagePath + '\'' +
                ", secondImagePath='" + secondImagePath + '\'' +
                ", year=" + year +
                ", priority=" + priority +
                ", id=" + id +
                '}';
    }
}
