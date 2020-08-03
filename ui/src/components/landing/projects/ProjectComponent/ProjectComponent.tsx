import React from "react"
import styles from "./ProjectComponent.module.css"
import { Image, Icon, Card, Reveal, Popup } from "semantic-ui-react"
import { ProjectInfo } from '../../../../api/index'
import {BoldedTextComponent} from "../../../utility/BoldedTextComponent";

interface Props {
  projectData: ProjectInfo
}

const boldedWords = [
    'Swift', 'Spritekit', 'Ruby on Rails', 'FourSquare API', 'Amazon S3',
    'UHack 2018', 'Raspberry Pi', 'Python', 'Java', 'JavaFX'
]

export class ProjectComponent extends React.Component<Props, {}> {
  // TODO:
  // Randomize move & move right
  render() {
    let firstImagePath = '../../../../../assets/images/' + this.props.projectData.firstImagePath;
    return (
      <Popup
        header='Fun Fact'
        position='top center'
        content={this.props.projectData.funFact}
        trigger={
        <Card>
          <Reveal animated='move'>
            <Reveal.Content visible>
              <Image src={require('../../../../assets/images/' + this.props.projectData.firstImagePath)} />
            </Reveal.Content>
            <Reveal.Content hidden>
              <Image src={require('../../../../assets/images/' + this.props.projectData.secondImagePath)} />
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
              <BoldedTextComponent boldedWords={boldedWords} text={this.props.projectData.description} />
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
