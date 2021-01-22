import React from 'react';
import './Experience.css';
import {
  Segment, Header, Icon, Container, Item,
} from 'semantic-ui-react';
import Position from './Position';
import { positions } from '../../data/experienceData';

const Experience = () => (
  <>
    <Segment id="experience" raised className="ProjectsContainer">
      <Header
        as="h2"
        textAlign="center"
        icon
        className="ProjectsHeader"
      >
        <Icon name="suitcase" circular />
        Experience
      </Header>
      <Container centered className="Container">
        <Item.Group textAlign="center">
          { positions.map((position) => (<Position {...position} />)) }
        </Item.Group>
      </Container>
    </Segment>
  </>
);

export default Experience;
