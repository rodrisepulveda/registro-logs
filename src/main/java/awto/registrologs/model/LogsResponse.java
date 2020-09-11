package awto.registrologs.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LogsResponse {

	private List<LogResponse> logs;

}
