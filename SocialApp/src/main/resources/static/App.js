import '../static/App.css';

import Home from './components/home/home'
import Login from './components/login/login'
import SignUp from './components/login/signup'
import '../../../../node_modules/bootstrap/dist/css/bootstrap-grid.css';
import React, {Component} from 'react';
const client = require('./client');
import { BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";

import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import {Container, Form, FormControl} from "react-bootstrap";
//import {Button} from "bootstrap";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedIn: localStorage.getItem('token') ? true : false,
            username: '',
        };
    }

    // componentDidMount() {
    //     client({method: 'GET', path: '/home'}).done(response => {
    //         const value = response.data;
    //         this.setState({ value });
    //     });
    // }

    render() {
        return (
            <Router>
                <div className="App">
                     <Navbar className={"navbar"} bg="dark" variant="dark" fixed={"top"}>
                         <Container>
                         <Navbar.Brand href="/home">Navbar</Navbar.Brand>
                         <Nav className="mr-auto">
                             <Nav.Link href="/home">Home</Nav.Link>
                             <Nav.Link href="/api/auth/signin">Login</Nav.Link>
                             <Nav.Link href="/api/auth/signup">Register</Nav.Link>
                         </Nav>
                         <Form inline>
                             <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                             {/*<Button variant="outline-info">Search</Button>*/}
                         </Form>
                         </Container>
                     </Navbar>

                    <div className="auth-wrapper">
                        <div className="auth-inner">
                            <Switch>
                                <Route exact path='/home' component={Home}/>
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


export default App;
