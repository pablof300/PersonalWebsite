import React from "react"
import styles from "./MessageComponent.module.css"
import { Message, Icon } from "semantic-ui-react"

interface Props {
  title: string
  content: string
  type: string
}

export class MessageComponent extends React.Component<Props, {}> {
  private iconName: string
  private color: string

  constructor(props: Props) {
    super(props)
    if (this.props.type == 'failure') {
      this.iconName = 'times'
      this.color = 'red'
    } else {
      this.iconName = 'check'
      this.color = 'green'
    }
  }

  render() {
    return (
      <>
      <Message icon className={styles.Message}>
        <Icon name={this.iconName as 'times' | 'check'} color={this.color as 'green' | 'red'} />
        <Message.Content>
          <Message.Header>{this.props.title}</Message.Header>
          {this.props.content}
        </Message.Content>
      </Message>
      </>
    )
  }
}
