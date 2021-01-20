import React from 'react';
import { MenuComponent } from '../../components/navbar/index'
import { LandingScreen } from '../LandingScreen/index'
import { BrowserRouter as Router, Route } from "react-router-dom"
import './App.css';

function App() {
  return (
    <>
      <MenuComponent />
      <Router>
        <Route exact path="/" component={() => <LandingScreen />} />
        {/* <Route exact path="/login" component={() => <LoginComponent />} /> */}
        {/* <Route exact path="/dashboard" component={() => <DashboardComponent />} /> */}
      </Router>
    </>
  );
}

export default App;
