package service;


import org.junit.Ignore;
import org.junit.Test;

import domain.BitchuteUpload;
import domain.BitchuteVideo;

public class UploadServiceIT {

    private final UploadService uploadService = new UploadService();

    @Ignore
    @Test
    public void launchSeleniumBot() {
        BitchuteUpload bitchuteUpload = new BitchuteUpload("/usr/local/bin/chromedriver", "user@gmail.com", "pwd", false);
        BitchuteVideo video = new BitchuteVideo();
        video.setTitle("Title: Lorem Ipsum");
        video.setDescription("Description: Lorem Ipsum");
        video.setVideoPath("/tmp/lorem_video.mp4");
        video.setCoverPath("/lorem_video.jpg");
        uploadService.launchSeleniumBot(bitchuteUpload, video);
    }
}
