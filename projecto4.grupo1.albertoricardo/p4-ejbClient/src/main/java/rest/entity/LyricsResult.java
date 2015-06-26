package rest.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LyricsResult")
public class LyricsResult {
	
	@XmlElement(required=true,name="lyrics")
	private String lrc;

	public String getLyric() {
		return lrc;
	}

	public void setLyric(String lyric) {
		this.lrc = lyric;
	}
	
	

}