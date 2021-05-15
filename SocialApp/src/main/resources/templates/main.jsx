import React, { Component } from "react";
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, BrowserRouter, Link, Route, Switch} from 'react-router-dom';
import '../static/main.css';
import '../static/App.css';
import Home from "./components/home/home";
import Login from "./components/login/login";
import SignUp from "./components/login/signup";

class App extends Component {
    function

    render() {
        return (
            <Router>

                <div className="App">
                    <nav className="navbar navbar-expand-lg navbar-light fixed-top">
                        <div className="container">
                            <Link className="navbar-brand" to={"/sign-in"}>DiS</Link>
                            <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                                <ul className="navbar-nav ml-auto">
                                    <li className="nav-item">
                                        <Link className="nav-link" to={"/api/auth/home"}>Home</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to={"/sign-in"}>Login</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>

                    <div className="auth-wrapper">
                        <div className="auth-inner">
                            <Switch>
                                <Route exact path='/api/auth/home' component={Home}/>
                                <Route path="/api/auth/signin" component={Login}/>
                                <Route path="/api/auth/signup" component={SignUp}/>
                            </Switch>
                        </div>
                    </div>
                </div>
            </Router>
        );
    }
}

ReactDOM.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>,
    document.getElementById('react-mountpoint')
);