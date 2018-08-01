import React, { Component } from 'react';
import PostCard from '../PostCard/PostCard';
import './Board.css';

class Board extends Component {
    constructor() {
        super();

        this.state = {
            posts: [],
        };
       
    }

    componentDidMount() {
        this.getPost();

    }

   // async getPost() {
    getPost = async () => {
        const response = await fetch('http://localhost:8080/posts');
        const data = await response.json();
        console.log(data);
        this.setState({
            posts: data.posts,
        });
    }

    render() {
        return (
            <div className="board">{
                this.state.posts.map((post) => {
                    return (
                        <PostCard key={post.id} post ={post}/>
                    );
                })
            }

            </div>
        );
    }
}
export default Board;