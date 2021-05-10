import React, { Component } from "react";
import ReactDOM from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import '../static/main.css';
import App from "./App";
class Main extends Component {
    render() {
        return (
            <div id="main">
                <h1>Demo Component</h1>
                <img src="https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg"/>
            </div>
        );
    }
}

ReactDOM.render(
    <BrowserRouter>
        <App />
    </BrowserRouter>,
    document.getElementById('react-mountpoint')
);