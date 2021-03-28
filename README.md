# bitchute-uploader
Library to upload a video on bitchute with a Selenium bot

## How to use it? 

### Create a new `BitchuteVideo`:

```java
BitchuteVideo video = new BitchuteVideo();
video.setTitle("My new uncensored Bitchute video");
video.setDescription("Hi guys! This is my new video. Thanks for watching!");
video.setVideoPath("/tmp/lorem_video.mp4");
video.setCoverPath("/tmp/lorem_video.jpg");
```

### Create a new `BitchuteUpload` (credentials & utils): 

```java
BitchuteUpload bitchuteUpload = new BitchuteUpload("/path/to/your/chromedriver", "your_user@mail.com", "your_user_pwd", false);
```
- `chrome driver path`: you can download a chromedriver here https://chromedriver.chromium.org/downloads
- `user mail`: the mail address of your Bitchute account
- `user password`
- `headless mode`: boolean for activation of the headless mode. If you don't know what is the `headless mode`, check this page: https://developers.google.com/web/updates/2017/04/headless-chrome

Now, you can launch the Selenium bot with your `BitchuteUpload` object:
```java
bitchuteUpload.launchSeleniumBot(video);
```

Enjoy!
