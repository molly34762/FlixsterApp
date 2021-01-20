## Flixster

### User Stories

#### Features
- Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Allow video posts to be played in full-screen using the YouTubePlayerView.
- Trailers for popular movies are played automatically when the movie is selected.
  - When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- Add a play icon overlay to popular movies to indicate that the movie can be played.
- Apply data binding to remove boilerplate code.
- Add a rounded corners for the images using the Glide transformations.

### App Walkthough GIF
<img src="Flixster.gif"> <br>

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
