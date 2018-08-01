import React, { Component } from 'react';
import Menu from './components/Menu/Menu'
import Board from './components/Board/Board'
import logo from './logo.svg';
import './App.css';


class App extends Component {

  constructor() {
    super();

    this.createPost = this.createPost.bind(this);

  }

  async createPost(title, url) {
    const request = await fetch('http://localhost:8080/post', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        title,
        url,
      })
    });
    const response = request.json();
    console.log(response)
  }




  render() {
    return (
      <body>
      <div className="App">
        <div className="Banner">Space</div>
        <div className="Container">
          <div className="Board">
            <Board />
          </div>
          <div className="Menu">
            <Menu />
          </div>
        </div>
      </div>
      </body>
    );
  }
}

export default App;
