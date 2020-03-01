import React from "react"
import styles from "./ProjectComponent.module.css"
import { Image, Icon, Card, Reveal, Popup, Rating } from "semantic-ui-react"
import { ProjectInfo } from '../../../../api/index'

interface Props {
  projectData: ProjectInfo
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
        content={this.props.projectData.funFact}
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
              {this.props.projectData.name}
            </Card.Header>
            <Card.Meta textAlign="center">
              <a>{this.props.projectData.type}</a>
            </Card.Meta>
            <Card.Description>
              {this.props.projectData.description}
            </Card.Description>
          </Card.Content>
          <Card.Content extra>
            <a href={this.props.projectData.url}>
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
