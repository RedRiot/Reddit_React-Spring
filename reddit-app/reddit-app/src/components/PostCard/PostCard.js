import React, { Component } from 'react';
import PropTypes from 'prop-types'
import './PostCard.css';

class PostCard extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.post.id,
            score: this.props.post.score,
        };

    }
    edit = async (e) => {
        let title = prompt('Enter title');
        if (title != null) {
            let url = prompt('Enter url');
            if (url != null) {
                let reddit = prompt('Enter username');
                this.editPost(title, url, reddit)
            }
        }
    }

    editPost = async (title, url, reddit) => {
        const request = await fetch('http://localhost:8080/posts/' + this.state.id, {
            method: 'PUT',
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

    delete = async () => {
        const request = await fetch('http://localhost:8080/posts/' + this.state.id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        window.location.reload();
    }
    
    upvote = async () => {
        const request = await fetch('http://localhost:8080/posts/' + this.state.id + '/upvote', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        const response = await request.json();
        console.log(response)
        this.setState({
            score: response.score,
        })
    }
    downvote = async () => {
        const request = await fetch('http://localhost:8080/posts/' + this.state.id + '/downvote', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        console.log(request);
        const response = await request.json();
        this.setState({
            score: response.score,
        })
    }

    render() {
        return (
            <div className="postcard">
             <div className="cont">
                    <button className="vote" onClick={this.upvote}>UPVOTE</button>
                    <div className="score">{this.state.score}</div>
                    <button className="vote" onClick={this.downvote}>DOWNVOTE</button>
                </div>
                <div className="bottom">
                <div className="intro">
                        <div className="title"> {this.props.post.title}<span className="l">({this.props.post.url})</span> </div>
                        <div className="url"></div>
                </div>
                <div className="submitted">
                    <div className="box">submitted by {this.props.post.reddit}</div>
                    <div className="box">{this.props.post.timestamp}</div>
                    <a onClick={this.delete} style={{cursor: 'pointer'}} className="delete">Delete</a>
                    <a onClick={this.edit} style={{cursor: 'pointer'}}>Edit</a>
                </div>
                </div>
               
            </div>
        );
    }
}

PostCard.propTypes = {
    post: PropTypes.shape({
        title: PropTypes.string.isRequired,
    })
}
export default PostCard;