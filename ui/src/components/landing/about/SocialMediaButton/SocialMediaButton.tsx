import React from "react"
import styles from "./SocialMediaButton.module.css"
import { Icon, Button } from "semantic-ui-react"

interface Props {
  link: string
  icon: string
}

export class SocialMediaButton extends React.Component<Props, {}> {
  render() {
    return (
      <Button
        as="a"
        href={this.props.link}
        color="grey"
        size="huge"
        centered
        icon
        inverted
        circular
      >
        <Icon name={this.props.icon as "mail" | "linkedin" | "github"} color="grey" />
      </Button>
    )
  }
}
