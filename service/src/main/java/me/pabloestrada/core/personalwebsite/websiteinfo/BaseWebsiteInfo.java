package me.pabloestrada.core.personalwebsite.websiteinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public class BaseWebsiteInfo
{
    @JsonProperty(required = true)
    private String firstParagraphOfAboutMe;

    @JsonProperty(required = true)
    private String secondParagraphOfAboutMe;

    @JsonProperty(required = true)
    private String listOfTechnicalLanguages;

    @JsonProperty(required = true)
    private String listOfFrameworks;

    @JsonProperty(required = true)
    private String listOfTools;

    @JsonProperty(required = true)
    private String listOfLanguages;

    @JsonProperty(required = true)
    private String resumePath;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(required = true)
    private ObjectId id;

    public BaseWebsiteInfo() {
        id = new ObjectId();
    }

    public BaseWebsiteInfo(final String firstParagraphOfAboutMe, final String secondParagraphOfAboutMe,
                           final String listOfTechnicalLanguages, final String listOfFrameworks, final String listOfTools,
                           final String listOfLanguages, final String resumePath)
    {
        this.firstParagraphOfAboutMe = firstParagraphOfAboutMe;
        this.secondParagraphOfAboutMe = secondParagraphOfAboutMe;
        this.listOfTechnicalLanguages = listOfTechnicalLanguages;
        this.listOfFrameworks = listOfFrameworks;
        this.listOfTools = listOfTools;
        this.listOfLanguages = listOfLanguages;
        this.resumePath = resumePath;
    }

    public String getFirstParagraphOfAboutMe() {
        return firstParagraphOfAboutMe;
    }

    public void setFirstParagraphOfAboutMe(String firstParagraphOfAboutMe) {
        this.firstParagraphOfAboutMe = firstParagraphOfAboutMe;
    }

    public String getSecondParagraphOfAboutMe() {
        return secondParagraphOfAboutMe;
    }

    public void setSecondParagraphOfAboutMe(String secondParagraphOfAboutMe) {
        this.secondParagraphOfAboutMe = secondParagraphOfAboutMe;
    }

    public String getListOfTechnicalLanguages() {
        return listOfTechnicalLanguages;
    }

    public void setListOfTechnicalLanguages(String listOfTechnicalLanguages) {
        this.listOfTechnicalLanguages = listOfTechnicalLanguages;
    }

    public String getListOfFrameworks() {
        return listOfFrameworks;
    }

    public void setListOfFrameworks(String listOfFrameworks) {
        this.listOfFrameworks = listOfFrameworks;
    }

    public String getListOfTools() {
        return listOfTools;
    }

    public void setListOfTools(String listOfTools) {
        this.listOfTools = listOfTools;
    }

    public String getListOfLanguages() {
        return listOfLanguages;
    }

    public void setListOfLanguages(String listOfLanguages) {
        this.listOfLanguages = listOfLanguages;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
