import React from 'react'

interface Props {
    boldedWords: string[]
    text: string
}

const Highlight : React.ComponentType<any> = (props) => (
    <strong className="highlighted-text">{props.children}</strong>
);

const BoldedText = (props: Props) => {
    const { boldedWords, text } = props
        return (
            <>
                <p>{text}</p>
            </>
        )
}
  
export default BoldedText

// <Highlighter
//     searchWords={boldedWords}
//     autoEscape={true}
//     highlightTag={Highlight}
//     textToHighlight={text}
// />