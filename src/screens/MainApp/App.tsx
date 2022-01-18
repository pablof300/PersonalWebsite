import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import MenuComponent from '../../components/Navbar';
import LandingScreen from '../LandingScreen';
// import createBrowserHistory from 'history'
// import ReactGA from 'react-ga';
import './App.css';

// const history = createBrowserHistory()
// ReactGA.initialize('UA-171727538-1');
// ReactGA.pageview(window.location.pathname + window.location.search);
// history.listen((location) => {
//   console.log("####????????")
//   ReactGA.pageview(location.pathname + location.search);
//   console.log(location)
// });

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
