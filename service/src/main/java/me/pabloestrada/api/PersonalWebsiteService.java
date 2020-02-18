package me.pabloestrada.api;

import me.pabloestrada.core.personalwebsite.websiteinfo.FullWebsiteInfo;

public abstract class PersonalWebsiteService {
    public abstract FullWebsiteInfo getWebsiteInfo();
    public abstract void updateBaseWebsiteInfo(final String firstParagraphOfAboutMe, final String secondParagraphOfAboutMe,
                                               final String listOfTechnicalLanguages, final String listOfFrameworks,
                                               final String listOfTools, final String listOfLanguages, final String resumePath);
    public abstract void updateProjectInfo(final String name, final String type, final String description, final String funFact,
                                           final String url, final String imagePath, final int year, final String id);
    public abstract void addProjectInfo(final String name, final String type, final String description, final String funFact,
                                        final String url, final String imagePath, final int year);
}