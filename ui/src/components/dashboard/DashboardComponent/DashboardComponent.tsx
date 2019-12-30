import React from "react"
import Cookies from 'js-cookie'
import styles from "./DashboardComponent.module.css"
import { AuthApi } from "../../../api/index"
import { Redirect } from 'react-router-dom'
import {
  Grid,
  Segment,
  Icon,
  Header,
  Button,
  Divider,
  Form,
  Input,
  Sidebar,
  Menu,
  Image,
  Card,
  Container,
  List,
  Label,
  Accordion
} from "semantic-ui-react"

interface State {
  authenticated: boolean
  loading: boolean
}

export class DashboardComponent extends React.Component<{}, State> {
  private authApi: AuthApi = new AuthApi()

  constructor(props: {}) {
    super(props)
    this.state = {
      authenticated: false,
      loading: true
    }
  }

  componentDidMount() {
    const token = Cookies.get('jwt')
    if (token) {
      const requestParams = { token: token }
      console.log("Found JWT token, veryfing...")
      this.authApi.verifyJWT(requestParams).then(verified => {
        this.setState({ authenticated: verified, loading: false })
        console.log("JWT token status is " + verified)
      })
    } else {
      console.log("No JWT token available")
      this.setState({ authenticated: false, loading: false })
    }
  }

  render() {
    if (this.state.loading) {
      return (<> </>)
    }
    if (!this.state.authenticated) {
      return (<Redirect to='/login' />)
    }
    return (
      <>
      <Container className={styles.Container}>
        <Grid padded className={styles.Grid} columns={4}>
          <Grid.Column>
            <Card className={styles.Card}>
              <Card.Content>
                <Image
                  floated='right'
                  size='mini'
                  src='https://react.semantic-ui.com/images/avatar/large/steve.jpg'
                />
                <Card.Header>Personal Website</Card.Header>
                <Card.Meta>status: online</Card.Meta>
                <Card.Description>
                  <Accordion styled>
                  <Accordion.Title
                    active={false}
                    index={0}
                  >
                    <Icon name='dropdown' />
                    description
                  </Accordion.Title>

                  <Accordion.Content active={false}>
                  <List bulleted>
                    <List.Item>Manage biography</List.Item>
                    <List.Item>Add projects</List.Item>
                    <List.Item>View inquiry messages</List.Item>
                    <List.Item>Update resume pdf</List.Item>

                  </List>
                  </Accordion.Content>
                  </Accordion>
                  <Accordion styled className={styles.Spacer}>
                    <Accordion.Title
                      active={false}
                      index={0}
                    >
                      <Icon name='dropdown' />
                      updated
                    </Accordion.Title>
                    <Accordion.Content active={false}>
                      June 23, 2016
                    </Accordion.Content>
                  </Accordion>

                </Card.Description>
              </Card.Content>
              <Card.Content extra>
                <div className='ui two buttons'>
                  <Button basic color='green'>
                    Access
                  </Button>
                </div>
              </Card.Content>
            </Card>
          </Grid.Column>
        </Grid>
      </Container>
      </>
    )
  }
}
