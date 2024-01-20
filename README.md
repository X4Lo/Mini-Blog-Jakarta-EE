## Description
Mini-Blog-Jakarta-EE is a simple web-based blogging application developed using Jakarta EE. This application allows users to create, modify, and delete blog posts. Each post can be commented on by users.

## Features
- **Post Creation**: Users can create blog posts with a title, content, and banner image.
- **Edit and Delete Posts**: Post authors can edit or delete their posts.
- **Comments**: Users can comment on blog posts.
- **User Management**: User registration and authentication, with account lock and retry attempts.
## Technologies Used
- **Jakarta EE**: For the backend and web application logic.
- **MySQL**: As the database for storing posts, users, and comments.
- **Bootstrap 5**: For designing a responsive and modern frontend.

## Environment Setup
To run this project, you will need a Jakarta EE server setup and a MySQL database. Here are the basic steps to set up the environment:
1. **Install a Jakarta EE Application Server** (such as GlassFish, WildFly, etc.).
2. **Set Up a MySQL Database**: Configure a MySQL database for the application.
3. **Create Database Tables**: Refer to the database schema below.

## Database Schema
The project uses the following tables:
- **post**: Stores information about blog posts.
    - `id`: Primary key, auto-incremented.
    - `title`: Title of the post.
    - `content`: Content of the post.
    - `banner`: URL or path of the banner image.
    - `authorId`: Foreign key to the `user` table.
    - `timestamp`: Timestamp of post creation/modification.
- **user**: Stores user information.
    - `id`: Primary key, auto-incremented.
    - `username`: Username.
    - `password`: Password (stored in MD5).
    - `picture`: URL or path of the profile image.
    - `attempts`: Number of login attempts.
    - `locked`: Account lock status.
- **comment**: Stores comments on posts.
    - `id`: Primary key, auto-incremented.
    - `postId`: Foreign key to the `post` table.
    - `authorId`: Foreign key to the `user` table.
    - `content`: Content of the comment.
    - `timestamp`: Timestamp of comment creation.

## Usage
1. **Clone the Project**: Clone this repository to your server.
2. **Deploy the Application**: Deploy the application on your Jakarta EE application server.
3. **Access the Application**: Open the application in a web browser.
