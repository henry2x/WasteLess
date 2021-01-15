package grocer.Logging;

public class ParsedLog {
	
	int event_id;
	String level_string;
	String thread_name;
	String formatted_message;
	
	public ParsedLog() {
		
	}

	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getLevel_string() {
		return level_string;
	}
	public void setLevel_string(String level_string) {
		this.level_string = level_string;
	}
	public String getThread_name() {
		return thread_name;
	}
	public void setThread_name(String thread_name) {
		this.thread_name = thread_name;
	}

	public String getFormatted_message() {
		return formatted_message;
	}
	public void setFormatted_message(String formatted_message) {
		this.formatted_message = formatted_message;
	}
	
	
}
