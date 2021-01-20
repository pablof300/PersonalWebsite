import React from 'react';
import { Icon, Button } from 'semantic-ui-react';

interface Props {
    link: string
    icon: string
  }

const SocialMediaButton = (props: Props) => {
  const { link, icon } = props;
  return (
    <Button
      as="a"
      href={link}
      color="grey"
      size="huge"
      centered
      icon
      inverted
      circular
    >
      <Icon name={icon as 'mail' | 'linkedin' | 'github'} color="grey" />
    </Button>
  );
};

export default SocialMediaButton;
