import React from "react"
import styles from "./ProjectComponent.module.css"
import { ProjectInfo } from "../../../../../api/index"
import { Card, Button, Label, Form } from "semantic-ui-react"

interface Props {
  projectData: ProjectInfo
  sendProjectData: (projectData: ProjectInfo) => Promise<string>
  deleteProject: (id: string) => void
}

export class ProjectComponent extends React.Component<Props, ProjectInfo> {
  constructor(props: Props) {
    super(props)
    this.state = props.projectData
    this.submitProjectInfo = this.submitProjectInfo.bind(this)
    this.isNewProject = this.isNewProject.bind(this)
  }


  componentWillReceiveProps(nextProps: Readonly<Props>, nextContext: any): void {
    if (this.isNewProject()) {
      return
    }
    this.setState(nextProps.projectData)
  }

  // TODO:
  // Refactor this to be abstracted in its own module
  isNewProject() {
    return this.state.id.length === 0
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
    // TODO:
    // Abstract options to another module
    const priorityOptions = [
      { text: '1', value: 1 },
      { text: '2', value: 2 },
      { text: '3', value: 3 },
      { text: '4', value: 4 },
      { text: '5', value: 5 },
      { text: '6', value: 6 },
      { text: '7', value: 7 },
      { text: '8', value: 8 },
      { text: '9', value: 9 },
      { text: '10', value: 10 },
      ];
    const yearOptions = [
      { text: '2017', value: 2017},
      { text: '2018', value: 2018 },
      { text: '2019', value: 2019 },
      { text: '2020', value: 2020 },
      { text: '2021', value: 2021 },
      { text: '2022', value: 2022 }
    ];

    return (
      <Card centered fluid>
        <Label color={newProject ? "green" : "blue"}>
          {newProject ? "new project" : "existing project"}
        </Label>
        <Card.Content>
          <Form>
            <Form.Group>
              <Form.Input
                  fluid
                  label="Project name"
                  value={this.state.name}
                  width={4}
                  onChange={(e: any) => this.setState({ name: e.target.value })}
              />
              <Form.Input
                  fluid
                  label="Type"
                  value={this.state.type}
                  width={4}
                  onChange={(e: any) => this.setState({ type: e.target.value })}
              />
              <Form.Input
                  fluid
                  label="Url"
                  value={this.state.url}
                  width={6}
                  onChange={(e: any) => this.setState({ url: e.target.value })}
              />
              <Form.Select
                  fluid
                  options={yearOptions}
                  label="Year"
                  value={this.state.year == 0 ? "" : this.state.year}
                  width={2}
                  onChange={(e: any, data: any) =>
                      this.setState({ year: data.value })
                  }
              />
            </Form.Group>
            <Form.Group>
              <Form.TextArea
                  fluid
                  label="Description"
                  value={this.state.description}
                  width={8}
                  onChange={(e: any) =>
                      this.setState({ description: e.target.value })
                  }
              />
              <Form.TextArea
                  fluid
                  label="Fun fact"
                  value={this.state.funFact}
                  width={8}
                  onChange={(e: any) => this.setState({ funFact: e.target.value })}
              />
            </Form.Group>
            <Form.Group>
              <Form.Input
                  fluid
                  label="First Image"
                  value={this.state.firstImagePath}
                  width={7}
                  onChange={(e: any) =>
                      this.setState({ firstImagePath: e.target.value })
                  }
              />
              <Form.Input
                  fluid
                  label="Second Image"
                  value={this.state.secondImagePath}
                  width={7}
                  onChange={(e: any) => this.setState({ secondImagePath: e.target.value })}
              />
              <Form.Select
                  fluid
                  options={priorityOptions}
                  label="Priority"
                  value={this.state.priority == -1 ? "" : this.state.priority}
                  width={2}
                  onChange={(e: any, data: any) => this.setState({ priority: data.value })}
              />
            </Form.Group>
            <Button
              onClick={this.submitProjectInfo}
              color={newProject ? "green" : "blue"}
            >
              {newProject ? "Create" : "Update"}
            </Button>
            <Button color={'red'} disabled={this.isNewProject()} onClick={e => this.props.deleteProject(this.state.id)}>
              Delete
            </Button>
          </Form>
        </Card.Content>
      </Card>
    )
  }
}
