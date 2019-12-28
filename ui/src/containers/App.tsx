import React from 'react';
import './App.css'

import { LandingComponent } from '../components/landing/LandingComponent/index'
import { LoginComponent } from '../components/login/LoginComponent/index'
import { MenuComponent } from '../components/menubar/MenuComponent/index'
import { BrowserRouter as Router, Route, Link } from "react-router-dom"

const App: React.FC = () => {
  return (
    <>
      <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css" />
      <MenuComponent />
      <Router>
        <Route exact path="/" component={() => <LandingComponent />} />
        <Route exact path="/login" component={() => <LoginComponent />} />
      </Router>
    </>
  );
}

export default App;
