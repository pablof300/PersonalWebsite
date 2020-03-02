import React from "react"
import styles from "./ProjectsComponent.module.css"
import { PersonalWebsiteApi, ProjectInfo } from "../../../../../api/index"
import {
  Grid,
  Icon,
  Card,
  Header,
  Message
} from "semantic-ui-react"
import { ProjectComponent } from "../ProjectComponent/index"
import Cookies from "js-cookie";

interface Props {
  personalWebsiteApi: PersonalWebsiteApi
  projects: ProjectInfo[]
}

interface State {
  updateMessage: string
  projects: ProjectInfo[]
}

export class ProjectsComponent extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = { projects: props.projects, updateMessage: "" }
    this.sendProjectData = this.sendProjectData.bind(this)
    this.addNewProject = this.addNewProject.bind(this)

    this.addNewProject()
  }

  addNewProject() {
    let currentProjects = this.state.projects
    if (this.isNewProject(currentProjects[currentProjects.length - 1])) {
      return
    }
    currentProjects.push({
      description: "",
      funFact: "",
      id: "",
      imagePath: "",
      name: "",
      type: "",
      url: "",
      year: 0
    })
    this.setState({ projects: currentProjects })
  }

  async sendProjectData(projectData: ProjectInfo): Promise<string> {
    const requestData = {
      bearerAuth: Cookies.get("jwt"),
      name: projectData.name,
      type: projectData.type,
      description: projectData.description,
      funFact: projectData.funFact,
      url: projectData.url,
      imagePath: projectData.imagePath,
      year: projectData.year
    }
    if (this.isNewProject(projectData)) {
      return this.props.personalWebsiteApi
        .addProjectInfo(requestData)
        .then(id => {
          this.addNewProject()
          this.setState({ updateMessage: "Added new project" })
          return id
        })
        .catch(error => {
          console.log(error)
          return ""
        })
    } else {
      return this.props.personalWebsiteApi
        .updateProjectInfo({...requestData, id: projectData.id})
        .then(id => {
          return "Successful"
        })
        .catch(error => {
          console.log(error)
          return "Failure"
        })
    }
  }

  // TODO:
  // Refactor this to be abstracted in its own module
  isNewProject(projectData: ProjectInfo) {
    return projectData.id.length == 0
  }

  render() {
    return (
      <Card fluid>
        <Card.Content>
          {this.state.updateMessage && (
            <Message>{this.state.updateMessage}</Message>
          )}
          <Grid>
            <Grid.Row centered>
              <Header as="h1">
                <Icon name="question circle" />
                Projects
              </Header>
            </Grid.Row>
            <Grid.Row>
              <Card.Content className={styles.Padded}>
                <Card.Group centered fluid itemsPerRow={5}>
                  {this.state.projects.map(projectData => {
                    return (
                      <Card>
                        <ProjectComponent
                          projectData={projectData}
                          sendProjectData={this.sendProjectData}
                        />
                      </Card>
                    )
                  })}
                </Card.Group>
              </Card.Content>
            </Grid.Row>
          </Grid>
        </Card.Content>
      </Card>
    )
  }
}
