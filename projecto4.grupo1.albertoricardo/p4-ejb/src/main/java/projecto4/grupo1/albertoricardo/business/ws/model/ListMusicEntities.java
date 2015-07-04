package projecto4.grupo1.albertoricardo.business.ws.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ListMusicEntities {

	private List<MusicDetail> listOfMusics;

	public ListMusicEntities() {
		listOfMusics = new ArrayList<MusicDetail>();
	}



	public List<MusicDetail> getListOfMusics() {
		return listOfMusics;
	}



	public void setListOfMusics(List<MusicDetail> listOfMusics) {
		this.listOfMusics = listOfMusics;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		for (MusicDetail md:listOfMusics) {
			String owner = "";
			if (md.getUserOwnerID() == 0) owner = "sem proprietário";
			else owner = "submetida pelo utilizador com id "+md.getUserOwnerID();
			sb.append("Nome: "+md.getTitle()+", Artista: "+md.getArtist()+", Album: "+md.getAlbum()+", Data: "+df.format(md.getDateRecord())+", "+owner+"\n");
		}
		return sb.toString();
	}





}
