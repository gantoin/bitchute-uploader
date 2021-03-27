package domain;

import service.UploadService;

public class BitchuteUpload {

    private final String chromeDriver;

    private final String user;

    private final String password;

    private final boolean headless;

    public BitchuteUpload(String chromeDriver, String user, String password, boolean headless) {
        this.chromeDriver = chromeDriver;
        this.user = user;
        this.password = password;
        this.headless = headless;
    }

    public void uploadVideo(BitchuteVideo video) {
        UploadService uploadService = new UploadService();
        uploadService.launchSeleniumBot(this, video);
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isHeadless() {
        return headless;
    }
}
