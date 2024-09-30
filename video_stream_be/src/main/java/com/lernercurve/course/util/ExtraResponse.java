package com.lernercurve.course.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ExtraResponse<X> {
	private String responseKey;
	private X responseValue;
	@SuppressWarnings("unused")
	private ExtraResponse() {}

}
