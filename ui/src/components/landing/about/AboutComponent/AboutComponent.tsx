import React from "react"
import styles from "./AboutComponent.module.css"
import { SocialMediaButton } from '../SocialMediaButton/index'
import avatar from '../../../../assets/images/avatar.jpeg'
import {
  Grid,
  Image,
  Segment,
  Header,
  Divider,
  Icon
} from "semantic-ui-react"
import {BoldedTextComponent} from "../../../utility/BoldedTextComponent";

interface Props {
  paragraphs: {first: string, second: string}
}

const Highlight : React.ComponentType<any> = (props) => (
  <strong className="highlighted-text">{props.children}</strong>
);

export class AboutComponent extends React.Component<Props, {}> {
  constructor(props: Props) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid stackable>
          <Grid.Column width={4} />
          <Grid.Column width={8} className={styles.MainContainer}>
            <Segment className={styles.MainCard} padded="very" piled>
              <Image
                size="small"
                circular
                centered
                src={avatar}
              />
              <Header as="h1" textAlign="center" className={styles.Title}>
                Hello, I am Pablo Estrada.
              </Header>
              <Divider horizontal>
                <Header as="h4">
                  <Icon name="question circle" />
                  About me
                </Header>
              </Divider>
              <BoldedTextComponent boldedWords={[]} text={this.props.paragraphs.first} />
              <Divider hidden />
              <BoldedTextComponent boldedWords={["University of Florida"]} text={this.props.paragraphs.second} />
              <Divider horizontal>
                <Header as="h4">
                  <Icon name="address book" />
                </Header>
              </Divider>
              <Grid centered stackable className={styles.SocialMediaContainer}>
                <SocialMediaButton icon="mail" link="#contact" />
                <SocialMediaButton icon="linkedin" link="https://www.linkedin.com/in/pablo-estrada/" />
                <SocialMediaButton icon="github" link="https://github.com/pablof300" />
              </Grid>
            </Segment>
          </Grid.Column>
        </Grid>
      </>
    )
  }
}
