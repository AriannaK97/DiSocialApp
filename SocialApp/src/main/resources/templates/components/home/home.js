import React from 'react';

export default class Home extends React.Component {
    constructor(props) {
      super(props);
      this.state = {value: ''};
    }


    componentDidMount() {
        fetch("/home")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        value: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }
  
    render() {
      return (
        <div class="about">
            <h3>Welcome to DiS <br/> The Social App for the di community <br/> {this.state.value} </h3>
        </div>
      );
    }
  }
