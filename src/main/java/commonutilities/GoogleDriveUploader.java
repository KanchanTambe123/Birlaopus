package commonutilities;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleDriveUploader {

    public static final String APPLICATION_NAME = "QA Automation GDrive";
    public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static final String TOKENS_DIRECTORY_PATH = "tokens";
    public static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);

    public static final String CREDENTIALS_FILE_PATH =
            "./src/main/resources/dtin-internal-projects.json";

    // Folder ID inside your Shared Drive
    public static final String PARENT_FOLDER_ID =
            "1uSggWZ8tzM20e8RGyx7rUmREJXkbp2qE";

    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT,
                        JSON_FACTORY,
                        clientSecrets,
                        SCOPES)
                        .setDataStoreFactory(
                                new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline")
                        .build();

        LocalServerReceiver receiver =
                new LocalServerReceiver.Builder().setPort(8085).build();

        return new AuthorizationCodeInstalledApp(flow, receiver)
                .authorize("user");
    }

    public static void uploadFileToDrive(List<String> attachmentPaths)
            throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT =
                GoogleNetHttpTransport.newTrustedTransport();

        Drive service = new Drive.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Create timestamp folder
        String folderName =
                new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

        File folderMetadata = new File();
        folderMetadata.setName(folderName);
        folderMetadata.setMimeType("application/vnd.google-apps.folder");
        folderMetadata.setParents(Collections.singletonList(PARENT_FOLDER_ID));

        File folder = service.files()
                .create(folderMetadata)
                .setSupportsAllDrives(true)
                .setFields("id,name")
                .execute();

        System.out.println("Folder Created : " + folder.getName());
        System.out.println("Folder ID      : " + folder.getId());

        // Upload all reports
        for (String reportPath : attachmentPaths) {

            java.io.File localFile = new java.io.File(reportPath);

            if (!localFile.exists()) {
                System.out.println("File not found : " + reportPath);
                continue;
            }

            String mimeType;

            if (reportPath.toLowerCase().endsWith(".html")) {
                mimeType = "text/html";
            } else if (reportPath.toLowerCase().endsWith(".pdf")) {
                mimeType = "application/pdf";
            } else if (reportPath.toLowerCase().endsWith(".zip")) {
                mimeType = "application/zip";
            } else {
                mimeType = "application/octet-stream";
            }

            File fileMetadata = new File();
            fileMetadata.setName(localFile.getName());
            fileMetadata.setParents(Collections.singletonList(folder.getId()));

            FileContent mediaContent =
                    new FileContent(mimeType, localFile);

            File uploadedFile = service.files()
                    .create(fileMetadata, mediaContent)
                    .setSupportsAllDrives(true)
                    .setFields("id,name")
                    .execute();

            System.out.println("Uploaded : " + uploadedFile.getName());
            System.out.println("File ID  : " + uploadedFile.getId());
        }

        System.out.println("All reports uploaded successfully.");
    }
}