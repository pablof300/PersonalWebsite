import React from "react"
import styles from "./LoginComponent.module.css"
import { AuthApi } from "../../../api/index"
import {
  Grid,
  Segment,
  Icon,
  Header,
  Button,
  Divider,
  Form,
  Input
} from "semantic-ui-react"

interface State {
  username: string
  password: string
}

export class LoginComponent extends React.Component<{}, State> {
  private authApi: AuthApi = new AuthApi()

  constructor(props: {}) {
    super(props)

    this.setUsername = this.setUsername.bind(this)
    this.setPassword = this.setPassword.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.getJWT = this.getJWT.bind(this)
  }

  setUsername(e: React.ChangeEvent<HTMLInputElement>) {
    this.setState({ username: e.target.value })
  }

  setPassword(e: React.ChangeEvent<HTMLInputElement>) {
    this.setState({ password: e.target.value })
  }

  handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    this.getJWT()
  }

  async getJWT() {
    const l = { username: this.state.username, password: this.state.password }
    this.authApi
      .getJWT(l)
      .then(token => {
        console.log("The response is ")
        console.log(token)
      })
      .catch(e => {
        console.log(e)
      })
  }

  render() {
    return (
      <>
        <Grid stackable>
          <Grid.Column width={4} />
          <Grid.Column width={8} className={styles.Margin}>
            <Segment padded="very" stacked className={styles.Container}>
              <Header
                centered
                textAlign="center"
                as="div"
                icon
                className={styles.Header}
              >
                <Icon name="lock" />
                Login
              </Header>
              <Divider hidden />
              <Segment raised color="teal">
                <Form onSubmit={this.handleSubmit}>
                  <Form.Field
                    label="Username"
                    control={Input}
                    onChange={this.setUsername}
                  />
                  <Form.Input
                    label="Password"
                    control={Input}
                    type="password"
                    onChange={this.setPassword}
                  />
                  <Button primary type="submit">
                    Login
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
