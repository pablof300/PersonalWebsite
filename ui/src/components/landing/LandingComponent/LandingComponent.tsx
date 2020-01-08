import React from "react"
import styles from "./LandingComponent.module.css"
import { AboutComponent } from "../about/AboutComponent/index"
import { ProjectsComponent } from "../projects/ProjectsComponent/index"
import { DownIconComponent } from "../DownIconComponent/index"
import { SkillsComponent } from "../skills/SkillsComponent/index"
import { ResumeComponent } from "../ResumeComponent/index"
import { ContactComponent } from "../ContactComponent/index"

interface Props {
  name: string
}

interface State {
  count: number
  developerName: string
}

export class LandingComponent extends React.Component<{}, State> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
        <AboutComponent />
        <DownIconComponent />
        <ProjectsComponent />
        <DownIconComponent />
        <SkillsComponent />
        <DownIconComponent />
        <ResumeComponent />
        <DownIconComponent />
        <ContactComponent />
      </>
    )
  }
}
