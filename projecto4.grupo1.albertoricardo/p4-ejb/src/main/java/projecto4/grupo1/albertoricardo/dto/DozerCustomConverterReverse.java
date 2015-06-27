package projecto4.grupo1.albertoricardo.dto;

import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;

import projecto4.grupo1.albertoricardo.MusicEntity;

public class DozerCustomConverterReverse implements CustomConverter {

	public Object convert(Object source, Object destination, 
			Class destClass, Class sourceClass) {
		if (source == null) {
			return null;
		}
		MusicEntity dest = null;
		if (source instanceof MusicDTO) {
			// check to see if the object already exists
			if (destination == null) {
				dest = new MusicEntity();
			} else {
				dest = (MusicEntity) destination;
			}
			dest.setAlbum(((MusicDTO) source).getAlbum());
			dest.setArtist(((MusicDTO) source).getArtist());
			dest.setDateRecord(((MusicDTO) source).getDateRecord());
			dest.setId(((MusicDTO) source).getId());
			return dest;
		} else {
			throw new MappingException("Converter TestCustomConverter "
					+ "used incorrectly. Arguments passed in were:"
					+ destination + " and " + source);
		}
	}
}