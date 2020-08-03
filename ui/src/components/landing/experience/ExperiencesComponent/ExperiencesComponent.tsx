import React from "react"
import styles from "./ExperiencesComponent.module.css"
import {Segment, Header, Icon, Card, Popup, Reveal, Image} from "semantic-ui-react"
import { ProjectInfo } from '../../../../api/index'
import Item from "semantic-ui-react/dist/commonjs/views/Item";
import Label from "semantic-ui-react/dist/commonjs/elements/Label";
import Container from "semantic-ui-react/dist/commonjs/elements/Container";



export class ExperiencesComponent extends React.Component<{}, {}> {
  render() {
    return (
        <>
          <Segment id={'experience'} raised className={styles.ProjectsContainer}>
            <Header
                as="h2"
                textAlign="center"
                icon
                className={styles.ProjectsHeader}
            >
              <Icon name="suitcase" circular/>
              Experience
            </Header>
            <Container centered className={styles.Container}>
              <Item.Group textAlign={'center'}>
                <Item className={styles.Item}>
                  <Item.Image src={require('../../../../assets/images/codekids_logo.png')}/>
                  <Item.Content>
                    <Item.Header as='a'>CodeKids</Item.Header>
                    <Item.Meta>
                      <span className='cinema'>Intern</span>
                    </Item.Meta>
                    <Item.Meta>
                      <span className='cinema'>Summer 2016</span>
                    </Item.Meta>
                    <Item.Description>Interned at a local summer technology camp, centered around engineering and programming for kids. Throughout three months, I had the opportunity to teach over 30 campers (ages 8-12 years) a curriculum of introductory programming, robot design, electronics, and engineering principles.</Item.Description>
                    <Item.Extra>
                      <Label color={'teal'}>Engineering</Label>
                      <Label color={'teal'}>Creativity</Label>
                      <Label color={'teal'}>Teaching</Label>
                    </Item.Extra>
                  </Item.Content>
                </Item>
                <Item className={styles.Item}>
                  <Item.Image  src={require('../../../../assets/images/two_sigma.png')}/>
                  <Item.Content>
                    <Item.Header as='a'>Two Sigma</Item.Header>
                    <Item.Meta>
                      <span className='cinema'>Software Engineer Intern</span>
                    </Item.Meta>
                    <Item.Meta>
                      <span className='cinema'>Summer 2019</span>
                    </Item.Meta>
                    <Item.Description>Worked as a software engineering intern at the operations team of Two Sigma, a quantitative hedge fund, in Houston. I developed a visualization tool for the Kafka network to provide better metrics, health checks and alerts, and wider understanding of the all the interactions between services. I was able to experience the domain of finance throughout that summer.</Item.Description>
                    <Item.Extra>
                      <Label color={'teal'}>Kafka</Label>
                      <Label color={'teal'}>Dropwizard</Label>
                      <Label color={'teal'}>React</Label>
                      <Label color={'teal'}>Java</Label>
                      <Label color={'teal'}>2019</Label>
                    </Item.Extra>
                  </Item.Content>
                </Item>
                <Item className={styles.Item}>
                  <Item.Image src={require('../../../../assets/images/lyft.jpg')}/>
                  <Item.Content>
                    <Item.Header as='a'>Lyft Level 5</Item.Header>
                    <Item.Meta>
                      <span className='cinema'>Software Engineer Intern</span>
                    </Item.Meta>
                    <Item.Meta>
                      <span className='cinema'>Summer 2020</span>
                    </Item.Meta>
                    <Item.Description>Virtually interned at Lyft Level 5, Lyft's self driving division in Palo Alto. I improved Level 5's web tools, used for autonomous ride playback and analysis, by migrating its web analytics to store data in BigQuery using gRPC. My project increased web analytics by 10% and refactored eight dashboards to provide better visualization of tool usage.</Item.Description>
                    <Item.Extra>
                      <Label color={'teal'}>gRPC</Label>
                      <Label color={'teal'}>BigQuery</Label>
                      <Label color={'teal'}>TypeScript</Label>
                      <Label color={'teal'}>Autonomous Vehicles</Label>
                    </Item.Extra>
                  </Item.Content>
                </Item>
              </Item.Group>
            </Container>
          </Segment>
        </>
    );
  }
}
