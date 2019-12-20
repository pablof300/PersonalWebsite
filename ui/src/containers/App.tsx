import React from 'react';
import './App.css'

import { LandingComponent } from '../components/landing/LandingComponent/index'
import { BrowserRouter as Router, Route, Link } from "react-router-dom"

const App: React.FC = () => {
  return (
    <>
      <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css" />
      <Router>
        <Route exact path="/" component={() => <LandingComponent />} />
      </Router>
    </>
  );
}

export default App;
