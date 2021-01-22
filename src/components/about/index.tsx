import React from 'react';
import {
  Grid,
  Image,
  Segment,
  Header,
  Divider,
  Icon,
} from 'semantic-ui-react';
import './About.css';
import avatar from '../../assets/images/avatar.jpeg';
import BoldedText from '../util/BoldedText';
import SocialMediaButton from '../util/SocialMediaButton';
import socialMediaData from '../../data/socialMediaData';

interface Props {
  firstParagraph: string;
  secondParagraph: string
}

const About = (props: Props) => {
  const { firstParagraph, secondParagraph } = props;

  return (
    <>
      <Grid stackable>
        <Grid.Column width={4} />
        <Grid.Column width={8} className="MainContainer">
          <Segment className="MainCard" padded="very" piled>
            <Image
              size="small"
              circular
              centered
              src={avatar}
            />
            <Header as="h1" textAlign="center" className="Title">
              Hello, I am Pablo Estrada.
            </Header>
            <Divider horizontal>
              <Header as="h4">
                <Icon name="question circle" />
                About me
              </Header>
            </Divider>
            <BoldedText boldedWords={['University of Florida']} text={firstParagraph} />
            <Divider hidden />
            <BoldedText boldedWords={[]} text={secondParagraph} />
            <Divider horizontal>
              <Header as="h4">
                <Icon name="address book" />
              </Header>
            </Divider>
            <Grid centered stackable className="SocialMediaContainer">
              <SocialMediaButton icon="mail" link={socialMediaData.mailLink} />
              <SocialMediaButton icon="linkedin" link={socialMediaData.linkedInLink} />
              <SocialMediaButton icon="github" link={socialMediaData.githubLink} />
            </Grid>
          </Segment>
        </Grid.Column>
      </Grid>
    </>
  );
};

export default About;
