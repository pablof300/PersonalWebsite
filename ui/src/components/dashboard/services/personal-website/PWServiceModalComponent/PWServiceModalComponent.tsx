import React from "react"
import styles from "./PersonalWebsiteServiceModalComponent.module.css"
import {
  PersonalWebsiteApi,
  ProjectInfo
} from "../../../../../api/index"
import {
  Icon,
  Header,
  Button,
  Card,
  Divider,
  Modal,
  Segment,
  Form,
  TextArea
} from "semantic-ui-react"
import { ProjectsComponent } from "../ProjectsComponent"
import Cookies from "js-cookie";

interface State {
  isDataLoaded: boolean

  firstParagraphOfAboutMe: string
  secondParagraphOfAboutMe: string
  listOfTechnicalLanguages: string
  listOfFrameworks: string
  listOfTools: string
  listOfLanguages: string
  resumePath: string
  id: string
  projectInfoList: Array<ProjectInfo>
}

export class PWServiceModalComponent extends React.Component<{}, State> {
  private api: PersonalWebsiteApi = new PersonalWebsiteApi()

  constructor(props: {}) {
    super(props)
    this.state = {
      isDataLoaded: false,
      firstParagraphOfAboutMe: "",
      secondParagraphOfAboutMe: "",
      listOfTechnicalLanguages: "",
      listOfFrameworks: "",
      listOfTools: "",
      listOfLanguages: "",
      resumePath: "",
      id: "",
      projectInfoList: []
    }

    this.getWebsiteInfo = this.getWebsiteInfo.bind(this)
    this.closeAndSubmitModal = this.closeAndSubmitModal.bind(this)
  }

  async getWebsiteInfo() {
    this.setState({ isDataLoaded: false })
    this.api.getWebsiteInfo().then(websiteInfo => {
      this.setState({ ...websiteInfo, isDataLoaded: true })
    })
  }

  closeAndSubmitModal() {
    this.api
      .updateBaseWebsiteInfo({
        bearerAuth: Cookies.get("jwt"),
        firstParagraphOfAboutMe: this.state.firstParagraphOfAboutMe,
        secondParagraphOfAboutMe: this.state.secondParagraphOfAboutMe,
        listOfTechnicalLanguages: this.state.listOfTechnicalLanguages,
        listOfFrameworks: this.state.listOfFrameworks,
        listOfTools: this.state.listOfTools,
        listOfLanguages: this.state.listOfLanguages,
        resumePath: this.state.resumePath
      })
      .then(() => {
        console.log("Successfully updated base website info")
      })
      .catch(e => {
        console.log(e)
      })
    this.setState({ isDataLoaded: false })
  }

  handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
  }

  render() {
    if (!this.state.isDataLoaded) {
      return (
        <Button basic color="green" onClick={this.getWebsiteInfo}>
          Access
        </Button>
      )
    }
    return (
      <Modal size="large" dimmer="blurring" open={this.state.isDataLoaded}>
        <Modal.Content scrolling>
          <Card.Group itemsPerRow={3} stackable>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  About me
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="First paragraph"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({
                          firstParagraphOfAboutMe: e.target.value
                        })
                      }
                    >
                      {this.state.firstParagraphOfAboutMe}
                    </Form.Field>
                    <Form.Input
                      onChange={(e: any) =>
                        this.setState({
                          secondParagraphOfAboutMe: e.target.value
                        })
                      }
                      label="Second paragraph"
                      control={TextArea}
                    >
                      {this.state.secondParagraphOfAboutMe}
                    </Form.Input>
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  Skills
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="List of technical languages"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({
                          listOfTechnicalLanguages: e.target.value
                        })
                      }
                    >
                      {this.state.listOfTechnicalLanguages}
                    </Form.Field>
                    <Form.Field
                      label="List of frameworks"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({ listOfFrameworks: e.target.value })
                      }
                    >
                      {this.state.listOfFrameworks}
                    </Form.Field>
                    <Form.Field
                      label="List of tools"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({ listOfTools: e.target.value })
                      }
                    >
                      {this.state.listOfTools}
                    </Form.Field>
                    <Form.Field
                      label="List of languages"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({ listOfLanguages: e.target.value })
                      }
                    >
                      {this.state.listOfLanguages}
                    </Form.Field>
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  Resume
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="List of tools"
                      control={TextArea}
                      onChange={(e: any) =>
                        this.setState({ resumePath: e.target.value })
                      }
                    >
                      {this.state.resumePath}
                    </Form.Field>
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
          </Card.Group>
          <ProjectsComponent
            personalWebsiteApi={this.api}
            projects={this.state.projectInfoList}
          />
        </Modal.Content>
        <Modal.Actions>
          <Button
            color={"red"}
            content="Close"
            icon="close"
            labelPosition="right"
            onClick={() => {
              this.setState({ isDataLoaded: false })
            }}
          />
          <Button
            primary
            content="Update Website"
            icon="right arrow"
            labelPosition="right"
            onClick={this.closeAndSubmitModal}
          />
        </Modal.Actions>
      </Modal>
    )
  }
}
