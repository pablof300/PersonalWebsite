import React from "react"
import styles from "./ProjectRowComponent.module.css"
import { ProjectComponent } from "../ProjectComponent/index"
import { Card } from "semantic-ui-react"
import { ProjectInfo } from '../../../../api/index'

interface Props {
  projects: ProjectInfo[]
}

export class ProjectRowComponent extends React.Component<Props, {}> {
  // TODO:
  // Create component ExperiencesComponent

  render() {
    return (
      <Card.Group centered stackable>
        {
          this.props.projects.map(projectData => {
            return <ProjectComponent projectData={projectData} />
          })
        }
      </Card.Group>
    )
  }
}
