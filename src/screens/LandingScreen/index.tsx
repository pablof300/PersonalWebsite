import React from 'react';
import About from '../../components/about';

const LandingScreen = () => {
  const firstParagraph = 'I am rising junior at the University of Florida, go Gators! '
    + 'I am studying computer science and statistics, and trying to learn other new things along the way.';
  const secondParagraph = 'I spend most of my time learning new languages and tools,'
    + ' with an interest in distributed systems, full-stack development, and finance.'
    + ' If I am not studying at Marston Library or hacking away, I am going for a run or playing ping pong.';

  return (
    <>
      <About firstParagraph={firstParagraph} secondParagraph={secondParagraph} />
    </>
  );
};

export default LandingScreen;
