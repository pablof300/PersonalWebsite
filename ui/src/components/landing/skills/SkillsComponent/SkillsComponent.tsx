import React from "react"
import styles from "./SkillsComponent.module.css"
import { Grid, Segment, Header, Icon } from "semantic-ui-react"
import { SkillComponent } from "../SkillComponent/index"

interface Props {
  techLanguages: string
  frameworks: string
  tools: string
  languages: string
}

export class SkillsComponent extends React.Component<Props, {}> {
  constructor(props: Props) {
    super(props)
  }

  render() {
    return (
      <>
        <Grid id={'skills'}>
          <Grid.Column width={2} />
          <Grid.Column width={12} centered className={styles.Container}>
            <Segment padded="very" piled className={styles.Segment}>
              <Grid stackable>
                <Grid.Row centered>
                  <Header as="h2" icon>
                    <Icon circular name="code" className={styles.Icon} />
                    Skills
                  </Header>
                </Grid.Row>
                <SkillComponent
                  type="Tech Languages"
                  list={this.props.techLanguages}
                />
                <SkillComponent
                  type="Frameworks"
                  list={this.props.frameworks}
                />
                <SkillComponent type="Tools" list={this.props.tools} />
                <SkillComponent
                  type="Languages"
                  list={this.props.languages}
                />
              </Grid>
            </Segment>
          </Grid.Column>
        </Grid>
      </>
    )
  }
}
