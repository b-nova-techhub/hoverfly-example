package com.bnova;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Techup
{
	@JsonProperty("id")
	private String id;

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("name")
	private String name;

	@JsonProperty("content")
	private String content;

	@JsonProperty("description")
	private String description;

	@JsonProperty("author")
	private String author;
}