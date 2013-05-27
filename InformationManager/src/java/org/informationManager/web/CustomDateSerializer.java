/**
 * 
 */
package org.informationManager.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * @author sony
 * 
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		if (null != value) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd");
			String formattedDate = formatter.format(value);
			jgen.writeString(formattedDate);
		}
	}
}