package projecto4.grupo1.albertoricardo.dto;

import java.util.Date;

public class PlaylistDTO {
	
	
	private int id;
	private String nome;
	private int size;
	private Date date;
	private int id_user;
	
	
	public PlaylistDTO() {
	}

	public PlaylistDTO(int id, String nome, int size, Date date, int id_user) {
		this.id = id;
		this.nome = nome;
		this.size = size;
		this.date = date;
		this.setId_user(id_user);
	}






	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
	
	
	
	
}
