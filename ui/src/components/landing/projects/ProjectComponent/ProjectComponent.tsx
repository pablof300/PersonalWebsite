import React from "react"
import styles from "./ProjectComponent.module.css"
import { Image, Icon, Card, Reveal, Popup, Rating } from "semantic-ui-react"

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

export class ProjectComponent extends React.Component<Props, {}> {
  constructor(props: Props) {
    super(props)
  }

  // TODO:
  // Randomize move & move right
  render() {
    return (
      <Popup
        header='Fun Fact'
        position='top center'
        content='After testing this game thousands of times, I have become really good at mancala.'
        trigger={
        <Card>
          <Reveal animated='move'>
            <Reveal.Content visible>
              <Image src='https://react.semantic-ui.com/images/avatar/large/matthew.png' />
            </Reveal.Content>
            <Reveal.Content hidden>
              <Image src='https://react.semantic-ui.com/images/avatar/large/chris.jpg' />
            </Reveal.Content>
          </Reveal>
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
        }
      / >
    )
  }
}
