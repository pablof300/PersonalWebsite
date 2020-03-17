import React from "react"
import styles from "./ResumeComponent.module.css"
import {
  Grid,
  Segment,
  Icon,
  Header,
  Button,
} from "semantic-ui-react"

export class ResumeComponent extends React.Component<{}, {}> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid id={'resume'} stackable className={styles.Margin}>
          <Grid.Column width={4} />
          <Grid.Column width={8}>
            <Segment
              textAlign="center"
              padded="very"
              piled
              className={styles.Container}
            >
              <Header as="div" icon className={styles.Header}>
                <Icon name="file alternate outline" />
                View my resume
              </Header>
              <Grid stackable centered className={styles.ButtonContainer}>
                <Button animated primary as="a" href="http://google.com">
                  <Button.Content visible>Pablo Estrada</Button.Content>
                  <Button.Content hidden>
                    <Icon name="arrow right" />
                  </Button.Content>
                </Button>
              </Grid>
            </Segment>
          </Grid.Column>
        </Grid>
      </>
    )
  }
}
