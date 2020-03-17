import React from "react"
import styles from "./ContactComponent.module.css"
import {
  Grid,
  Segment,
  Icon,
  Header,
  Button,
  Divider,
  Form,
  TextArea,
  Input
} from "semantic-ui-react"

export class ContactComponent extends React.Component<{}, {}> {
  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid id={'contact'} stackable className={styles.Margin}>
          <Grid.Column width={4} />
          <Grid.Column width={8}>
            <Segment padded="very" raised className={styles.Container}>
              <Header
                centered
                textAlign="center"
                as="div"
                icon
                className={styles.Header}
              >
                <Icon name="envelope outline" />
                Contact
              </Header>
              <Divider hidden />
              <Segment raised color="teal">
                <Form>
                  <Form.Field
                    label="Your name"
                    placeholder="Name"
                    control={Input}
                  />
                  <Form.Field
                    label="Your email"
                    placeholder="Email"
                    control={Input}
                  />
                  <Form.Field label="Your message" control={TextArea} />
                  <Button primary type="submit">
                    Submit
                  </Button>
                </Form>
              </Segment>
            </Segment>
          </Grid.Column>
        </Grid>
      </>
    )
  }
}
