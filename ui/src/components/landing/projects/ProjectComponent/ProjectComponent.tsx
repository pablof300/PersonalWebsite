import React from "react"
import styles from "./ProjectComponent.module.css"
import { Grid, Image, Segment, Icon, Card } from "semantic-ui-react"

interface Props {
  projectData: ProjectData
}

// Add enum for type
export interface ProjectData {
  image: string
  type: string
  title: string
  year: number
  link: string
  description: string
}

const a = (
  <a>
    <Icon name="user" />
    16 Friends
  </a>
)

export class ProjectComponent extends React.Component<Props, {}> {
  constructor(props: Props) {
    super(props)
  }

  render() {
    return (
      <>
        <Card>
          <Image
            src="https://react.semantic-ui.com/images/avatar/large/matthew.png"
            wrapped
            ui={false}
          />
          <Card.Content>
            <Card.Header textAlign="center">
              {this.props.projectData.title}
            </Card.Header>
            <Card.Meta textAlign="center">
              <a>Team Project</a>
            </Card.Meta>
            <Card.Description>
              {this.props.projectData.description}
            </Card.Description>
          </Card.Content>
          <Card.Content extra>
            <a href={this.props.projectData.link}>
              <Icon name="linkify" />
              View more
            </a>
            <a className={styles.RightFloated}>
              Developed in {this.props.projectData.year}
            </a>
          </Card.Content>
        </Card>
      </>
    )
  }
}

// <Card
//   image='https://react.semantic-ui.com/images/avatar/large/elliot.jpg'
//   header={this.props.projectData.title}
//   meta='Friend'
//   description='Elliot is a sound engineer living in Nashville who enjoys playing guitar and hanging with his cat.'
//   extra={a}
// />
