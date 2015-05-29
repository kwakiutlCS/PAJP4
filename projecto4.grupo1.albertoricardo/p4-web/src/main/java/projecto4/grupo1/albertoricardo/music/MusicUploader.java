package projecto4.grupo1.albertoricardo.music;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import projecto4.grupo1.albertoricardo.MusicUploadEJBLocal;
import projecto4.grupo1.albertoricardo.date.DateChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class MusicUploader implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String artist;
	private String album;
	private Date dateReleased;
	private String path;

	@EJB
	private MusicUploadEJBLocal mu;

	private Part file;


	public void fileUpload() throws IOException {
		path = System.getProperty("user.dir");
		File folder = new File(path+"/music/");
		String fileName = getFilename(file);
		System.out.println("Nome do ficheiro: "+fileName);
		InputStream inputStream = file.getInputStream();          
		if (folder.exists()) {
			FileOutputStream outputStream = new FileOutputStream(new File(folder,fileName));
			byte[] buffer = new byte[4096];          
			int bytesRead = 0;  
			while(true) {                          
				bytesRead = inputStream.read(buffer);  
				if(bytesRead > 0) {  
					outputStream.write(buffer, 0, bytesRead);  
				} else {  
					break;  
				}                         
			}  
			outputStream.close();  
			inputStream.close();
		} else {
			folder.mkdir();
			FileOutputStream outputStream = new FileOutputStream(new File(folder,fileName));
			byte[] buffer = new byte[4096];          
			int bytesRead = 0;  
			while(true) {                          
				bytesRead = inputStream.read(buffer);  
				if(bytesRead > 0) {  
					outputStream.write(buffer, 0, bytesRead);  
				} else {  
					break;  
				}                         
			}  
			outputStream.close();  
			inputStream.close();
		}
		String finalPath = folder.getAbsolutePath() + "/" + fileName;
		mu.uploadMusicDB(title, artist, album, dateReleased, finalPath);
	}

	private static String getFilename(Part part) {  
		for (String cd : part.getHeader("content-disposition").split(";")) {  
			if (cd.trim().startsWith("filename")) {  
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
			}  
		}  
		return null;  
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
	}


	public Date getDateReleased() {
		return dateReleased;
	}


	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Part getFile() {
		return file;
	}


	public void setFile(Part file) {
		System.out.println();
		this.file = file;
	}

}
