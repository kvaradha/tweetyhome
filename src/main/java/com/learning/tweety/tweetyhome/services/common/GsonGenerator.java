package com.learning.tweety.tweetyhome.services.common;

import java.lang.reflect.Type;
import java.sql.Date;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

@Service
public class GsonGenerator {
	/**
	 * customize buildGson to parse date field in JSON request.
	 * @return
	 */
	public Gson buildGson() {
		GsonBuilder builder = new GsonBuilder(); 
		// Register an adapter to manage the date types as long values 
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			} 
		});
		return builder.create();
	}
}
