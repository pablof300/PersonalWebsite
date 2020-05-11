import React from "react"
import styles from "./ProjectsComponent.module.css"
import { Segment, Header, Icon } from "semantic-ui-react"
import { ProjectRowComponent } from "../ProjectRowComponent/index"
import { ProjectInfo } from '../../../../api/index'

interface Props {
  projects: ProjectInfo[]
}


export class ProjectsComponent extends React.Component<Props, {}> {
  getProjectsInGroupsOfThree(): Array<Array<ProjectInfo>> {
    const numberOfProjectsInRow = 3
    let projects: Array<Array<ProjectInfo>> = []
    for (let i = 0; i < this.props.projects.length; i += numberOfProjectsInRow) {
      projects.push(this.props.projects.slice(i, i + numberOfProjectsInRow))
    }
    return projects
  }

  render() {
    return (
      <>
        <Segment id={'projects'} raised className={styles.ProjectsContainer}>
          <Header
            as="h2"
            textAlign="center"
            icon
            className={styles.ProjectsHeader}
          >
            <Icon name="code" circular />
            Projects
          </Header>
          { this.getProjectsInGroupsOfThree().map(projects => {
            return <ProjectRowComponent projects={projects} />
            })
          }
        </Segment>
      </>
    )
  }
}
