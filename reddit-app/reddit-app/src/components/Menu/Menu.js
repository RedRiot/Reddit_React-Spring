import React, { Component } from 'react';
import './Menu.css';

class Menu extends Component {
    constructor(props) {
        super(props)

        this.state = {
            title: '',
            url: '',
            reddit: '',
        };
        this.createPost = this.createPost.bind(this);
    }


    submit = async (e) => {
        let title = prompt('Enter title');
        if (title != null) {
            let url = prompt('Enter url');
            if (url != null) {
                let reddit = prompt('Enter username');
                this.createPost(title, url, reddit)
            }
        }
    }

    createPost = async (title, url, reddit) => {
        const request = await fetch('http://localhost:8080/post', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title,
                url,
                reddit,
            })
        });
        const response = request.json();
        window.location.reload();
        console.log(response)
    }


    render() {
        return (
            <div className="menu"> 
                <button className="submit" onClick={this.submit.bind(this)}>SUBMIT A NEW POST</button>
                <div className="menucontainer">
                    <div className="about">About</div>
                    <div className="space">R/Space</div>
                </div>
                <div className="list">
                    <div>Share & discuss informative content on: </div>
                    <ul>
                        <li>Astrophysics</li>
                        <li>Cosmology</li>
                        <li>Space Exploration</li>
                        <li>Planetary Science</li>
                        <li>Astrobiology</li>
                    </ul>
                </div>
            </div>
        );
    }
}

export default Menu;