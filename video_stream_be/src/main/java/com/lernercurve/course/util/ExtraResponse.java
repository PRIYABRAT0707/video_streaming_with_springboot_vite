package com.lernercurve.course.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraResponse<X> {
	private String responseKey;
	private X responseValue;

}
