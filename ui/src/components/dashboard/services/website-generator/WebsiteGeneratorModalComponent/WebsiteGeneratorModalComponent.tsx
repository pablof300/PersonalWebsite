import React from "react"
import styles from "./WebsiteGeneratorModalComponent.module.css"
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
  Divider,
  Modal,
  Segment,
  Form,
  Input,
  TextArea
} from "semantic-ui-react"

interface State {
}

export class WebsiteGeneratorModalComponent extends React.Component<{}, State> {

  constructor(props: {}) {
    super(props)
  }

  handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
  }

  render() {
    return (
      <Modal size='fullscreen' dimmer='blurring' trigger={
        <Button basic color="green">
          Access
        </Button>
      }>
        <Modal.Content scrolling>
          <Card.Group itemsPerRow={3} stackable>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  About me
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="First paragraph"
                      control={TextArea}
                    />
                    <Form.Input
                      label="Second paragraph"
                      control={TextArea}
                    />
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  Skills
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="First paragraph"
                      control={Input}
                    />
                    <Form.Input
                      label="Second paragraph"
                      control={Input}
                    />
                    <Form.Input
                      label="Second paragraph"
                      control={Input}
                    />
                    <Form.Input
                      label="Second paragraph"
                      control={Input}
                    />
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
            <Card>
              <Card.Content>
                <Header
                  centered
                  textAlign="center"
                  as="div"
                  icon
                  className={styles.Header}
                >
                  <Icon name="lock" />
                  Resume
                </Header>
                <Divider hidden />
                <Segment raised color="teal">
                  <Form onSubmit={this.handleSubmit}>
                    <Form.Field
                      label="Resume"
                      control={Input}
                    />
                  </Form>
                </Segment>
              </Card.Content>
            </Card>
          </Card.Group>
          <Card fluid>
            <Card.Content>
              <Header
                centered
                textAlign="center"
                as="div"
                icon
                className={styles.Header}
              >
                <Icon name="lock" />
                Projects
              </Header>
              <Divider hidden />
              <Segment raised color="teal">
                <Form onSubmit={this.handleSubmit}>
                  <Form.Field
                    label="Resume"
                    control={Input}
                  />
                </Form>
              </Segment>
            </Card.Content>
          </Card>
        </Modal.Content>
        <Modal.Actions>
          <Button primary content='Update Website' icon='right arrow' labelPosition='right' />
        </Modal.Actions>
      </Modal>
    )
  }
}
