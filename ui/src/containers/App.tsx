import React from 'react';
import './App.css'

import { LandingComponent } from '../components/landing/LandingComponent/index'
import { LoginComponent } from '../components/login/LoginComponent/index'
import { DashboardComponent } from '../components/dashboard/DashboardComponent/index'
import { MenuComponent } from '../components/menubar/MenuComponent/index'
import { BrowserRouter as Router, Route, Link } from "react-router-dom"

const App: React.FC = () => {
  return (
    <>
      <MenuComponent />
      <Router>
        <Route exact path="/" component={() => <LandingComponent />} />
        <Route exact path="/login" component={() => <LoginComponent />} />
        <Route exact path="/dashboard" component={() => <DashboardComponent />} />
      </Router>
    </>
  );
}

export default App;
