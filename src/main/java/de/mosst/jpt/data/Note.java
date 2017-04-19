package de.mosst.jpt.data;

import lombok.Data;

@Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Note {

	private String id;
	private String title;
	private String text;
	private boolean encrypted;

}
