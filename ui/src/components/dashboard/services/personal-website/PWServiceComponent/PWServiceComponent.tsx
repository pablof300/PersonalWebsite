import React from "react"
import styles from "./PWServiceComponent.module.css"
import { PWServiceModalComponent } from "../PWServiceModalComponent/index"
import { Grid, Icon, Image, Card, Accordion } from "semantic-ui-react"
import logo from '../../../../../assets/images/personal-website-service.webp'

interface State {
  activeIndex: number
}

export class PWServiceComponent extends React.Component<
  {},
  State
> {
  constructor(props: {}) {
    super(props)
    this.state = { activeIndex: 3 }
  }

  handleClick = (e: any, titleProps: any) => {
    const { index } = titleProps
    const { activeIndex } = this.state
    const newIndex = activeIndex === index ? -1 : index

    this.setState({ activeIndex: newIndex })
  }

  render() {
    return (
      <Grid.Column>
        <Card className={styles.Card}>
          <Card.Content>
            <Image
              floated="right"
              size="mini"
              src={logo}
            />
            <Card.Header>Personal Website</Card.Header>
            <Card.Meta>status: online</Card.Meta>
            <Card.Description>
              <Accordion styled>
                <Accordion.Title active={this.state.activeIndex === 0} onClick={this.handleClick} index={0}>
                  <Icon name="dropdown" />
                  description
                </Accordion.Title>
                <Accordion.Content active={this.state.activeIndex === 0}>
                  Manage what content goes on the landing website of
                  pabloestrada.me. You can modify biography, projects, resume,
                  skills, and contact information.
                </Accordion.Content>
              </Accordion>
              <Accordion styled className={styles.Spacer}>
                <Accordion.Title active={this.state.activeIndex === 1}  onClick={this.handleClick} index={1}>
                  <Icon name="dropdown" />
                  updated
                </Accordion.Title>
                <Accordion.Content active={this.state.activeIndex === 1}>
                  June 23, 2016
                </Accordion.Content>
              </Accordion>
            </Card.Description>
          </Card.Content>
          <Card.Content extra>
            <div className="ui two buttons">
              <PWServiceModalComponent />
            </div>
          </Card.Content>
        </Card>
      </Grid.Column>
    )
  }
}
