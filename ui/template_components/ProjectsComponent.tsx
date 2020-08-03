import React from "react"
import styles from "./ProjectsComponent.module.css"
import { ProjectComponent, ProjectData } from "../ExperienceComponent/index"
import { Segment, Header, Icon, Card } from "semantic-ui-react"

interface Props {
  name: string
}

// Add <strong> to technologies in maybe the backend?

{$project_data}

const singularities: ProjectData = {
  image: "singularities",
  title: "Singularities",
  type: "Hackathon project",
  description:
    "Lead developer of Singularities, an iOS game for iPhone and iPad. Developed using Swift and SpriteKit as a two-dimensional retro gravity game. It has been downloaded more than 500 times on the AppStore with a review of 4.8 stars.",
  year: 2018,
  link: "https://itunes.apple.com/us/app/singularities/id1278565563"
}

export class ProjectsComponent extends React.Component<{}, {}> {
  constructor(props: {}) {
    super(props)
  }

  // Create componenet ExperiencesComponent

  render() {
    return (
      <>
        <Segment raised className={styles.ProjectsContainer}>
          <Header
            as="h2"
            textAlign="center"
            icon
            className={styles.ProjectsHeader}
          >
            <Icon name="code" circular />
            Projects
          </Header>
          <Card.Group centered>
            <ProjectComponent projectData={singularities} />
            <ProjectComponent projectData={singularities} />
            <ProjectComponent projectData={singularities} />
            <ProjectComponent projectData={singularities} />
            <ProjectComponent projectData={singularities} />
          </Card.Group>
          <Card.Group centered>
            <ProjectComponent projectData={singularities} />
            <ProjectComponent projectData={singularities} />
          </Card.Group>
        </Segment>
      </>
    )
  }
}
