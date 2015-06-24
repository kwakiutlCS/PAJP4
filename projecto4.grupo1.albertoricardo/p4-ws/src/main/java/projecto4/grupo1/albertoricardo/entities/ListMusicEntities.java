package projecto4.grupo1.albertoricardo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import projecto4.grupo1.albertoricardo.MusicEntity;

@XmlRootElement
public class ListMusicEntities {
	private List<MusicEntity> list = new ArrayList<>();

	public List<MusicEntity> getList() {
		return list;
	}

	public void setList(List<MusicEntity> list) {
		this.list = list;
	}
	
}
