import React from 'react';

interface Props {
    boldedWords: string[]
    text: string
}

const BoldedText = (props: Props) => {
  const { text, boldedWords } = props;
  // TODO - Add highlighter using boldedWords
  console.log(boldedWords);
  return (
    <>
      <p>{text}</p>
    </>
  );
};

export default BoldedText;
