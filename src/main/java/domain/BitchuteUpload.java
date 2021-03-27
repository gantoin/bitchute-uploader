package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import service.UploadService;

@AllArgsConstructor
@Getter
public class BitchuteUpload {

    private final String chromeDriver;

    private final String user;

    private final String password;

    private final boolean headless;
    
    public void uploadVideo(BitchuteVideo video) {
        UploadService uploadService = new UploadService();
        uploadService.launchSeleniumBot(this, video);
    }

}
