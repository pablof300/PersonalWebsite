import React from "react"
import styles from "./ETCardComponent.module.css"
import { Grid, Icon, Image, Card, Header, Divider, Button } from "semantic-ui-react"
import logo from '../../../../../assets/images/exercise-tracker-service.png'

interface State {
}

export class ETCardComponent extends React.Component<
  {},
  State
> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <Grid.Column width={6}>
        <Card className={styles.Card}>
          <Card.Content>
            <Image
              floated="right"
              size="mini"
              src={logo}
            />
            <Card.Header>ExerciseTracker</Card.Header>
            <Card.Meta>status: online</Card.Meta>
            <Card.Description>
              <Card centered>
                <Card.Content>
                  <Grid>
                    <Grid.Row columns={2} relaxed='very' >
                      <Grid.Column>
                        <Header textAlign={'center'}>distance ran
                          <Header.Subheader>
                            <h1>2.32</h1>
                          </Header.Subheader>
                          <Header.Subheader>
                            <p>miles</p>
                          </Header.Subheader>
                        </Header>
                      </Grid.Column>
                      <Grid.Column>
                        <Header textAlign={'center'}>time in gym
                          <Header.Subheader>
                            <h1>23</h1>
                          </Header.Subheader>
                          <Header.Subheader>
                            <p>mins</p>
                          </Header.Subheader>
                        </Header>
                      </Grid.Column>
                    </Grid.Row>
                    <Divider />
                    <Grid.Row columns={2} relaxed='very' >
                      <Grid.Column>
                        <Header textAlign={'center'}>streak
                          <Header.Subheader>
                            <h1>2</h1>
                          </Header.Subheader>
                          <Header.Subheader>
                            <p>days</p>
                          </Header.Subheader>
                        </Header>
                      </Grid.Column>
                      <Grid.Column>
                        <Header textAlign={'center'}>
                          <Header.Subheader>
                            <Button size={'small'} primary style={{marginBottom: '5px'}} >View stats</Button>
                            <Button size={'small'}>Add session</Button>
                          </Header.Subheader>
                        </Header>
                      </Grid.Column>
                    </Grid.Row>
                  </Grid>
                </Card.Content>
              </Card>
            </Card.Description>
          </Card.Content>
          <Card.Content extra>
            <div className="ui two buttons">
            </div>
          </Card.Content>
        </Card>
      </Grid.Column>
    )
  }
}
