package me.pabloestrada.core.personalwebsite.websiteinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.pabloestrada.core.personalwebsite.projects.ProjectInfo;

import java.util.List;

public class FullWebsiteInfo extends BaseWebsiteInfo
{
    @JsonProperty(required = true)
    private List<ProjectInfo> projectInfoList;

    public FullWebsiteInfo(final BaseWebsiteInfo info, final List<ProjectInfo> projectInfoList) {
        super(info.getFirstParagraphOfAboutMe(),
                info.getSecondParagraphOfAboutMe(),
                info.getListOfTechnicalLanguages(),
                info.getListOfFrameworks(),
                info.getListOfTools(),
                info.getListOfLanguages(),
                info.getResumePath()
        );
        this.projectInfoList = projectInfoList;
    }

    public List<ProjectInfo> getProjectInfoList() {
        return projectInfoList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
