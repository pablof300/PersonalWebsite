package me.pabloestrada.core.personalwebsite.websiteinfo;

import me.pabloestrada.core.personalwebsite.projects.ProjectInfo;

import java.util.List;

public class FullWebsiteInfo extends BaseWebsiteInfo
{
    private List<ProjectInfo> projectInfoList;

    public FullWebsiteInfo(final BaseWebsiteInfo info, final List<ProjectInfo> projectInfoList) {
        super(info.getFirstParagraphOfAboutMe(),
                info.getSecondParagraphOfAboutMe(),
                info.getListOfTechnicalLanguages(),
                info.getListOfFrameworks(),
                info.getListOfTools(),
                info.getResumePath(),
                info.getListOfLanguages()
        );
        this.projectInfoList = projectInfoList;
    }

    public List<ProjectInfo> getProjectInfoList() {
        return projectInfoList;
    }
}
