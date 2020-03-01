import React from "react"
import styles from "./ProjectRowComponent.module.css"
import { ProjectComponent } from "../ProjectComponent/index"
import { Segment, Header, Icon, Card } from "semantic-ui-react"
import { ProjectInfo } from '../../../../api/index'

interface Props {
  projects: ProjectInfo[]
}

export class ProjectRowComponent extends React.Component<Props, {}> {
  constructor(props: Props) {
    super(props)
  }

  // Create componenet ProjectRowComponent

  render() {
    return (
      <Card.Group centered stackable>
        {
          this.props.projects.map(projectData => {
            return <Card><ProjectComponent projectData={projectData} /></Card>
          })
        }
      </Card.Group>
    )
  }
}
