import React from "react"
import styles from "./ProjectsComponent.module.css"
import { PersonalWebsiteApi, ProjectInfo } from "../../../../../api/index"
import { Grid, Icon, Card, Header, Message } from "semantic-ui-react"
import { ProjectComponent } from "../ProjectComponent/index"
import Cookies from "js-cookie"

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
    this.deleteProject = this.deleteProject.bind(this)
  }

  deleteProject(id: string): void {
    this.props.personalWebsiteApi
      .deleteProjectInfo({ id: id, bearerAuth: Cookies.get("jwt") })
      .then(() => {
        let currentProjects: ProjectInfo[] = []
        console.log(this.state.projects.length)
        this.state.projects.forEach(project => {
          if (project.id !== id) {
            currentProjects.push(project)
          }
        })
        this.setState({ projects: currentProjects })
        console.log(this.state.projects.length)
      })
      .catch(error => {
        console.log(error)
      })
  }

  async sendProjectData(projectData: ProjectInfo): Promise<string> {
    const requestData = {
      name: projectData.name,
      type: projectData.type,
      description: projectData.description,
      funFact: projectData.funFact,
      url: projectData.url,
      firstImage: projectData.firstImagePath,
      secondImage: projectData.secondImagePath,
      priority: projectData.priority,
      year: projectData.year,
      bearerAuth: Cookies.get("jwt")
    }
    if (this.isNewProject(projectData)) {
      return this.props.personalWebsiteApi
        .addProjectInfo(requestData)
        .then(id => {
          projectData['id'] = id
          this.setState({
            updateMessage: "Added new project!",
            projects: [...this.state.projects, projectData]
          })
          return id
        })
        .catch(error => {
          console.log(error)
          return ""
        })
    } else {
      return this.props.personalWebsiteApi
        .updateProjectInfo({ ...requestData, id: projectData.id })
        .then(() => {
          this.setState({ updateMessage: "Updated project!" })
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
    return projectData.id.length === 0
  }

  render() {
    const newProject = {
      description: "",
      funFact: "",
      id: "",
      name: "",
      firstImagePath: "",
      secondImagePath: "",
      type: "",
      url: "",
      year: 0,
      priority: -1
    }

    let projects = this.state.projects.slice()
    projects.push(newProject)

    console.log(projects)

    return (
      <Card fluid>
        <Card.Content>
          {this.state.updateMessage && (
            <Message>{this.state.updateMessage}</Message>
          )}
          <Grid className={styles.VeryPadded}>
            <Grid.Row centered>
              <Header as="h1">
                <Icon name="question circle" />
                Projects
              </Header>
            </Grid.Row>
            {projects.map(projectData => {
              console.log(projectData)
              return (
                <Grid.Row padded centered>
                  <ProjectComponent
                    projectData={projectData}
                    sendProjectData={this.sendProjectData}
                    deleteProject={this.deleteProject}
                  />
                </Grid.Row>
              )
            })}
          </Grid>
        </Card.Content>
      </Card>
    )
  }
}
