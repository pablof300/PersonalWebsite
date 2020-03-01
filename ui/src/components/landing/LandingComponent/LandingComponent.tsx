import React from "react"
import styles from "./LandingComponent.module.css"
import { AboutComponent } from "../about/AboutComponent/index"
import { ProjectsComponent } from "../projects/ProjectsComponent/index"
import { DownIconComponent } from "../../utility/DownIconComponent/index"
import { SkillsComponent } from "../skills/SkillsComponent/index"
import { ResumeComponent } from "../resume/ResumeComponent/index"
import { ContactComponent } from "../contact/ContactComponent/index"
import { PersonalWebsiteApi, FullWebsiteInfo } from '../../../api/index'

interface State {
  websiteInfo?: FullWebsiteInfo
}

export class LandingComponent extends React.Component<{}, State> {
  private api: PersonalWebsiteApi = new PersonalWebsiteApi()

  constructor(props: {}) {
    super(props)
    this.state = { websiteInfo: undefined }
    this.getWebsiteInfo = this.getWebsiteInfo.bind(this)
    this.getWebsiteInfo()
  }

  async getWebsiteInfo() {
    this.api.getWebsiteInfo().then(websiteInfo => {
      this.setState({websiteInfo: websiteInfo})
    })
  }

  render() {
    const websiteInfo = this.state.websiteInfo;
    if (!websiteInfo) {
      return (<></>)
    }
    return (
      <>
        <AboutComponent paragraphs={{first: websiteInfo.firstParagraphOfAboutMe, second: websiteInfo.secondParagraphOfAboutMe}} />
        <DownIconComponent />
        <ProjectsComponent projects={websiteInfo.projectInfoList}/>
        <DownIconComponent />
        <SkillsComponent techLanguages={websiteInfo.listOfTechnicalLanguages} frameworks={websiteInfo.listOfFrameworks} tools={websiteInfo.listOfTools} languages={websiteInfo.resumePath} />
        <DownIconComponent />
        <ResumeComponent />
        <DownIconComponent />
        <ContactComponent />
      </>
    )
  }
}
