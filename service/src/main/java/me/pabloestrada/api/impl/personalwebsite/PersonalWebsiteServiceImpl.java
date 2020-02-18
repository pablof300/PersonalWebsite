package me.pabloestrada.api.impl.personalwebsite;

import com.google.inject.Inject;
import me.pabloestrada.api.PersonalWebsiteService;
import me.pabloestrada.core.personalwebsite.projects.ProjectInfo;
import me.pabloestrada.core.personalwebsite.websiteinfo.BaseWebsiteInfo;
import me.pabloestrada.core.personalwebsite.WebsiteInfoDAO;
import me.pabloestrada.core.personalwebsite.websiteinfo.FullWebsiteInfo;

public class PersonalWebsiteServiceImpl extends PersonalWebsiteService
{
    private final WebsiteInfoDAO websiteInfoDAO;

    @Inject
    public PersonalWebsiteServiceImpl(final WebsiteInfoDAO websiteInfoDAO) {
        this.websiteInfoDAO = websiteInfoDAO;
    }

    public FullWebsiteInfo getWebsiteInfo() {
        return new FullWebsiteInfo(websiteInfoDAO.getBaseWebsiteInfo(), websiteInfoDAO.getProjects());
    }

    public void updateBaseWebsiteInfo(final String firstParagraphOfAboutMe, final String secondParagraphOfAboutMe,
                                      final String listOfTechnicalLanguages, final String listOfFrameworks, final String listOfTools,
                                      final String listOfLanguages, final String resumePath) {
        websiteInfoDAO.updateBaseWebsiteInfo(
                new BaseWebsiteInfo(firstParagraphOfAboutMe, secondParagraphOfAboutMe, listOfTechnicalLanguages,
                        listOfFrameworks, listOfTools, listOfLanguages, resumePath)
        );
    }

    public void updateProjectInfo(final String name, final String type, final String description,
                                  final String funFact, final String url, final String imagePath, int year, final String id) {
        websiteInfoDAO.updateProjectInfo(new ProjectInfo(name, type, description, funFact, url, imagePath, year, id));
    }

    public void addProjectInfo(final String name, final String type, final String description,
                               final String funFact, final String url, final String imagePath, int year) {
        websiteInfoDAO.addProjectInfo(new ProjectInfo(name, type, description, funFact, url, imagePath, year));
    }
}
