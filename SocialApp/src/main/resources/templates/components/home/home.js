import React from 'react';
import client from '../../client'

export default class Home extends React.Component {
    constructor(props) {
      super(props);
      this.state = {value: ''};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/auth/home'}).done(response => {
            const value = response.data;
            this.setState({ value });
        });
    }
  
    render() {
      return (
        <div class="about">
            <h3>Welcome to DiS <br/> The Social App for the di community <br/> {this.state.value} </h3>
        </div>
      );
    }
  }
