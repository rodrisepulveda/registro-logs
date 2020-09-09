package awto.registrologs.model;

import java.util.List;

import lombok.Data;

@Data
public class Log {

	public String host;

	public String origin;

	public String details;

	public String stacktrace;

	public List<String> hashtags;

}
