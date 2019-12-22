import React from 'react';
import styles from './SkillsComponent.module.css'
import { Grid, Image, Segment, Header, Divider, Icon, Button } from 'semantic-ui-react'
import { SkillComponent } from '../SkillComponent/index'

export class SkillsComponent extends React.Component<{}, {}> {

  constructor(props: {}) {
    super(props)
  }

  render() {
    return (
      <>
      <Grid>
        <Grid.Column width={2} />
        <Grid.Column width={12} centered className={styles.Container}>
          <Segment padded='very' piled className={styles.Segment}>
            <Grid stackable>
              <Grid.Row centered>
                <Header as='h2' icon>
                <Icon circular name='code' className={styles.Icon} />
                  Skills
                </Header>
              </Grid.Row>
              <SkillComponent type='Tech Languages' list='Java, Ruby, Swift, C++, Python, HTML, CSS, JavaScript' />
              <SkillComponent type='Frameworks' list='Rails, SpriteKit, JavaFX' />
              <SkillComponent type='Tools' list='Git, Photoshop' />
              <SkillComponent type='Languages' list='English, Spanish (Fluent)' />
            </Grid>
          </Segment>
        </Grid.Column>
      </Grid>
      </>
    )
  }
}
