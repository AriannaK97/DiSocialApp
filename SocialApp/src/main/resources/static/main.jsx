import React, { Component } from "react";
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, BrowserRouter, Link, Route, Switch} from 'react-router-dom';
import '../static/App.css'
import App from "../static/App";
// import Navbar from "react-bootstrap/Navbar";
// import Nav from "react-bootstrap/Nav";
// import {Container, Form, FormControl} from "react-bootstrap";
// import {Button} from "bootstrap";
// import Home from "./components/home/home";
// import Login from "./components/login/login";
// import SignUp from "./components/login/signup";

// class App extends Component {
//
//     render() {
//         return (
//             <div className="App">
//                 <Navbar bg="dark" variant="dark" fixed={"top"}>
//                     <Container>
//                     <Navbar.Brand href="/home">Navbar</Navbar.Brand>
//                     <Nav className="mr-auto">
//                         <Nav.Link href="/home">Home</Nav.Link>
//                         <Nav.Link href="/api/auth/signin">Login</Nav.Link>
//                         <Nav.Link href="/api/auth/signup">Register</Nav.Link>
//                     </Nav>
//                     <Form inline>
//                         <FormControl type="text" placeholder="Search" className="mr-sm-2" />
//                         <Button variant="outline-info">Search</Button>
//                     </Form>
//                     </Container>
//                 </Navbar>
//             </div>
//
//         );
//     }
// }

ReactDOM.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>,
    document.getElementById('react-mountpoint')
);