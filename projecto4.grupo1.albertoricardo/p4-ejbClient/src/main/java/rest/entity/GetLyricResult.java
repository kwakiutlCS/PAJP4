package rest.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GetLyricResult",namespace="http://api.chartlyrics.com/")
public class GetLyricResult {
	
	@XmlElement(required=true,name="Lyric")
	private String lyrica;

	public String getLyric() {
		return lyrica;
	}

	public void setLyric(String lyric) {
		this.lyrica = lyric;
	}
	
	

}
