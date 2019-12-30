import React from "react"
import styles from "./AboutComponent.module.css"
import {
  Grid,
  Image,
  Segment,
  Header,
  Divider,
  Icon,
  Button
} from "semantic-ui-react"

interface Props {
  name: string
}

export class AboutComponent extends React.Component<{}, {}> {
  constructor(props: {}) {
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
                src="https://avatars0.githubusercontent.com/u/25207181?s=460&v=4"
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
              <p>
                I am software developer that likes to see technology solve
                everyday problems. I spend most of my time learning new
                languages and tools to make any of my crazy ideas a reality.
              </p>
              <Divider hidden />
              <p>
                I study computer science at the{" "}
                <strong>University of Florida</strong>, currently a freshmen,
                but if I am not hacking away or studying at the Library, I am
                going for a run or playing ping pong.
              </p>
              <Divider horizontal>
                <Header as="h4">
                  <Icon name="address book" />
                </Header>
              </Divider>
              <Grid centered stackable className={styles.SocialMediaContainer}>
                <Button
                  color="grey"
                  size="huge"
                  centered
                  icon
                  inverted
                  circular
                >
                  <Icon name="mail" color="grey" />
                </Button>
                <Button
                  color="grey"
                  size="huge"
                  centered
                  icon
                  inverted
                  circular
                >
                  <Icon name="linkedin" color="grey" />
                </Button>
                <Button
                  color="grey"
                  size="huge"
                  centered
                  icon
                  inverted
                  circular
                >
                  <Icon name="github" color="grey" />
                </Button>
              </Grid>
            </Segment>
          </Grid.Column>
        </Grid>
      </>
    )
  }
}
