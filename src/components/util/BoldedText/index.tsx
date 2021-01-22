import React from 'react';
import Highlighter from 'react-highlight-words';
import './BoldedText.css';

interface Props {
    boldedWords: string[]
    text: string
}

const Highlight : React.ComponentType<any> = (props: { children: JSX.Element | JSX.Element[] }) => {
  const { children } = props;
  return <span className="Highlight">{children}</span>;
};

const BoldedText = (props: Props) => {
  const { text, boldedWords } = props;
  return (
    <Highlighter
      searchWords={boldedWords}
      autoEscape
      highlightTag={Highlight}
      textToHighlight={text}
    />
  );
};

export default BoldedText;
