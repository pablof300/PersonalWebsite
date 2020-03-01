import React from "react"
import styles from "./ProjectComponent.module.css"
import { ProjectInfo } from "../../../../../api/index"
import { Card, Button, Label, Form, TextArea, Input } from "semantic-ui-react"

interface Props {
  projectData: ProjectInfo
  sendProjectData: (projectData: ProjectInfo) => Promise<string>
}

export class ProjectComponent extends React.Component<Props, ProjectInfo> {
  constructor(props: Props) {
    super(props)
    this.state = props.projectData
    this.submitProjectInfo = this.submitProjectInfo.bind(this)
    this.isNewProject = this.isNewProject.bind(this)
  }

  // TODO:
  // Refactor this to be abstracted in its own module
  isNewProject() {
    return this.state.id.length == 0
  }

  submitProjectInfo() {
    this.props.sendProjectData(this.state).then(id => {
      if (this.isNewProject()) {
        this.setState({ id: id })
      }
    })
  }

  render() {
    const newProject: boolean = this.isNewProject()
    return (
      <Card>
        <Label color={newProject ? "green" : "blue"}>
          {newProject ? "new project" : "existing project"}
        </Label>
        <Card.Content>
          <Form>
            <Form.Input
              fluid
              label="Project name"
              value={this.state.name}
              onChange={(e: any) => this.setState({ name: e.target.value })}
            />
            <Form.Input
              fluid
              label="Type"
              value={this.state.type}
              onChange={(e: any) => this.setState({ type: e.target.value })}
            />
            <Form.Input
              fluid
              label="Description"
              value={this.state.description}
              onChange={(e: any) =>
                this.setState({ description: e.target.value })
              }
            />
            <Form.Input
              fluid
              label="Fun fact"
              value={this.state.funFact}
              onChange={(e: any) => this.setState({ funFact: e.target.value })}
            />
            <Form.Input
              fluid
              label="Url"
              value={this.state.url}
              onChange={(e: any) => this.setState({ url: e.target.value })}
            />
            <Form.Input
              fluid
              label="Image"
              value={this.state.imagePath}
              onChange={(e: any) =>
                this.setState({ imagePath: e.target.value })
              }
            />
            <Form.Input
              fluid
              label="Year"
              value={this.state.year == 0 ? "" : this.state.year}
              onChange={(e: any) =>
                this.setState({ year: parseInt(e.target.value) })
              }
            />
            <Button
              onClick={this.submitProjectInfo}
              color={newProject ? "green" : "blue"}
            >
              {newProject ? "Create" : "Update"}
            </Button>
          </Form>
        </Card.Content>
      </Card>
    )
  }
}
