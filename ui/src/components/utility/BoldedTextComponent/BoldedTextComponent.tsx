import React from "react"
import Highlighter from "react-highlight-words";

interface Props {
  boldedWords: string[]
  text: string
}

const Highlight : React.ComponentType<any> = (props) => (
    <strong className="highlighted-text">{props.children}</strong>
);

export class BoldedTextComponent extends React.Component<Props, {}> {

  constructor(props: Props) {
    super(props)
  }

  render() {
    return (
        <Highlighter
            searchWords={this.props.boldedWords}
            autoEscape={true}
            highlightTag={Highlight}
            textToHighlight={this.props.text}
        />
    )
  }
}
