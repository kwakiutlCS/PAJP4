package projecto4.grupo1.albertoricardo.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AllPlaylists {
	
	public AllPlaylists() {
		// TODO Auto-generated constructor stub
	}
	
	public AllPlaylists(int id, String name, Date insertDate, int userOwnerID) {
		super();
		this.id = id;
		this.name = name;
		this.insertDate = insertDate;
		this.userOwnerID = userOwnerID;
	}
	private int id;
	private String name;
	private Date insertDate;
	private int userOwnerID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public int getUserOwnerID() {
		return userOwnerID;
	}
	public void setUserOwnerID(int userOwnerID) {
		this.userOwnerID = userOwnerID;
	}
	
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return "Playlist:\n"
				+ "Nome: "+name+"\n"
						+ "Data de criação: "+df.format(insertDate)+"\n"
								+ "(Criado pelo utilizador com ID "+userOwnerID+")";
	}
	
	

}
