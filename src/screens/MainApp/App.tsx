import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import MenuComponent from '../../components/Navbar';
import LandingScreen from '../LandingScreen';
import './App.css';

const App = () => (
  <>
    <MenuComponent />
    <Router basename={process.env.PUBLIC_URL}>
      <Route exact path="/" component={() => <LandingScreen />} />
      {/* <Route exact path="/login" component={() => <LoginComponent />} /> */}
      {/* <Route exact path="/dashboard" component={() => <DashboardComponent />} /> */}
    </Router>
  </>
);

export default App;
