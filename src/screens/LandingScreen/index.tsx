import React from 'react';
import About from '../../components/About';
import Experience from '../../components/Experience';
import DownIcon from '../../components/util/DownIcon';
import aboutData from '../../data/aboutData'

const LandingScreen = () => {
  return (
    <>
      <About firstParagraph={aboutData.firstParagraph} secondParagraph={aboutData.secondParagraph} />
      <DownIcon />
      <Experience  />
    </>
  );
};

export default LandingScreen;
