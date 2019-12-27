import React from 'react';
import styles from './BaseComponent.module.css'
// import { PersonalWebsiteApi } from '../../api/index'
import { Grid, Image, Segment } from 'semantic-ui-react'

import { ReactLogo } from '../ReactLogo/index'

interface Props {
  name: string
}

interface State {
  count: number
  developerName: string
}

export class BaseComponent extends React.Component<Props, State> {
  // private api: PersonalWebsiteApi = new PersonalWebsiteApi()

  constructor(props: Props) {
    super(props)
    this.state = { count: 0, developerName: "" }
    this.getDeveloperName = this.getDeveloperName.bind(this);
    this.getDeveloperName()
  }

  async getDeveloperName() {
    // this.api.getDeveloperName().then(devName => {
    //   this.setState({ developerName: devName })
    // })
  }

  render() {
    return (
      <>
      <Grid>
        <Grid.Column width={4} />
        <Grid.Column width={8}>
          <Segment className={styles.MainCard} padded='very' piled >Very padded content.</Segment>
        </Grid.Column>
      </Grid>
        <p className={styles.Text}>
          Hello {this.props.name}! The count is {this.state.count} and the developer name is {this.state.developerName}
        </p>

      </>
    )
  }
}
