package awto.registrologs.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LogResponse {

	private Integer id;

	private String host;

	private String origin;

	private String details;

	private String stacktrace;

	private List<String> hashtags;

}
