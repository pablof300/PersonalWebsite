import React from "react"
import Cookies from "js-cookie"
import styles from "./DashboardComponent.module.css"
import { AuthApi } from "../../../api/index"
import { Redirect } from "react-router-dom"
import { PersonalWebsiteServiceComponent } from "../services/personal-website/PWServiceComponent/index"
import {
  Grid,
  Icon,
  Header,
  Container,
  Divider
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
    const token = Cookies.get("jwt")
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
      return <> </>
    }
    if (!this.state.authenticated) {
      return <Redirect to="/login" />
    }
    return (
      <>
        <Header as="h1" icon textAlign="center" className={styles.Header}>
          <Icon name="servicestack" circular />
          <Header.Content>Persian Services</Header.Content>
        </Header>
        <Container className={styles.Container}>
        <Divider />
          <Grid padded stackable className={styles.Grid} columns={4}>
            <PersonalWebsiteServiceComponent />
          </Grid>
        </Container>
      </>
    )
  }
}
