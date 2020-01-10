import React from "react"
import styles from "./WebsiteGeneratorServiceComponent.module.css"
import { WebsiteGeneratorModalComponent } from "../WebsiteGeneratorModalComponent/index"
import {
  Grid,
  Icon,
  Header,
  Button,
  Image,
  Card,
  Container,
  List,
  Accordion,
  Divider
} from "semantic-ui-react"

interface State {
}

export class WebsiteGeneratorServiceComponent extends React.Component<{}, State> {

  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <Grid.Column>
        <Card className={styles.Card}>
          <Card.Content>
            <Image
              floated="right"
              size="mini"
              src="https://react.semantic-ui.com/images/avatar/large/steve.jpg"
            />
            <Card.Header>Personal Website</Card.Header>
            <Card.Meta>status: online</Card.Meta>
            <Card.Description>
              <Accordion styled>
                <Accordion.Title active={false} index={0}>
                  <Icon name="dropdown" />
                  description
                </Accordion.Title>
                <Accordion.Content active={false}>
                  Manage what content goes on the landing website of pabloestrada.me. You can modify biography, projects, resume, skills, and contact information.
                </Accordion.Content>
              </Accordion>
              <Accordion styled className={styles.Spacer}>
                <Accordion.Title active={false} index={0}>
                  <Icon name="dropdown" />
                  updated
                </Accordion.Title>
                <Accordion.Content active={false}>
                  June 23, 2016
                </Accordion.Content>
              </Accordion>
            </Card.Description>
          </Card.Content>
          <Card.Content extra>
            <div className="ui two buttons">
              <WebsiteGeneratorModalComponent />
            </div>
          </Card.Content>
        </Card>
      </Grid.Column>
    )
  }
}
