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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleDriveUploader {

	public static final String APPLICATION_NAME = "QA Automation GDrive";
	public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	public static final String TOKENS_DIRECTORY_PATH = "tokens";
	public static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
	public static final String CREDENTIALS_FILE_PATH = "./src/main/resources/dtin-internal-projects.json";
	public static final String SHARED_DRIVE_ID = "1ehw0bxGAbK_ZB1GmrG8Nadb304SQAGKq";
 
	public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

		// Load client secrets.
		FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8085).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static void uploadFileToDrive(List<String> attachmentPaths) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		// File's metadata.
		// create folder
		String folderName = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		File fileMetadata = new File();
		fileMetadata.setName(folderName);
		fileMetadata.setMimeType("application/vnd.google-apps.folder");
		fileMetadata.setParents(Collections.singletonList(SHARED_DRIVE_ID));//add driver path where to create folder on particuler location
		// create folder
		File folder = service.files().create(fileMetadata).setSupportsAllDrives(true).setFields("id").execute();
		System.out.println("Folder ID: " + folder.getId());
		// return folder.getId();

		// Upload the file.
		

		for (String reportPath : attachmentPaths) {
			File fileMetadata1 = new File();
			fileMetadata1.setName(new java.io.File(reportPath).getName());//file name

			String mimeType = reportPath.endsWith(".html") ? "text/html" : "application/pdf";
			FileContent mediaContent = new FileContent(mimeType, new java.io.File(reportPath));

			fileMetadata1.setParents(Collections.singletonList(folder.getId()));//add folder path where to upload file on particuler location

			File file = service.files().create(fileMetadata1, mediaContent).setFields("id").setSupportsAllDrives(true)
					.execute();
			System.out.println("File ID: " + file.getId());

			System.out.println("File upload complete");
		}

	}
}
