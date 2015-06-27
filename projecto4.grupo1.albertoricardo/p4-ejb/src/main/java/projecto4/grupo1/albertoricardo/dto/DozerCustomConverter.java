package projecto4.grupo1.albertoricardo.dto;

import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;

import projecto4.grupo1.albertoricardo.MusicEntity;

public class DozerCustomConverter implements CustomConverter {

	public Object convert(Object source, Object destination, 
			Class destClass, Class sourceClass) {
		if (source == null) {
			return null;
		}
		MusicDTO dest = null;
		if (source instanceof MusicEntity) {
			// check to see if the object already exists
			if (destination == null) {
				dest = new MusicDTO();
			} else {
				dest = (MusicDTO) destination;
			}
			dest.setAlbum(((MusicEntity) source).getAlbum());
			dest.setArtist(((MusicEntity) source).getArtist());
			dest.setDateRecord(((MusicEntity) source).getDateRecord());
			dest.setId(((MusicEntity) source).getId());
			return dest;
		} else {
			throw new MappingException("Converter TestCustomConverter "
					+ "used incorrectly. Arguments passed in were:"
					+ destination + " and " + source);
		}
	}
}