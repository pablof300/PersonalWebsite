import React from 'react';
import Highlighter from "react-highlight-words";
import './BoldedText.css'

interface Props {
    boldedWords: string[]
    text: string
}

const Highlight : React.ComponentType<any> = (props) => (
  <span className="Highlight"  >{props.children}</span>
);

const BoldedText = (props: Props) => {
  const { text, boldedWords } = props;
  // TODO - Add highlighter using boldedWords
  return (
    <Highlighter
        searchWords={boldedWords}
        autoEscape={true}
        highlightTag={Highlight}
        textToHighlight={text}
    />
)
};

export default BoldedText;
