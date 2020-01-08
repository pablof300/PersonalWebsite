import React from "react"
import styles from "./ProjectRowComponent.module.css"
import { ProjectComponent, ProjectData } from "../ProjectComponent/index"
import { Segment, Header, Icon, Card } from "semantic-ui-react"

interface Props {
  projects: ProjectData[]
}

// Add <strong> to technologies in maybe the backend?

const singularities: ProjectData = {
  image: "singularities",
  title: "Singularities",
  type: "Hackathon project",
  description:
    "Lead developer of Singularities, an iOS game for iPhone and iPad. Developed using Swift and SpriteKit as a two-dimensional retro gravity game. It has been downloaded more than 500 times on the AppStore with a review of 4.8 stars.",
  year: 2018,
  link: "https://itunes.apple.com/us/app/singularities/id1278565563"
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
