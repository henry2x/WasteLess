package grocer.Logging;

public class Log {
	
	int event_id;
	String logger_name;
	String level_string;
	String thread_name;
	String caller_class;
	String caller_method;
	String formatted_message;
	
	public Log() {
		
	}
	public Log(int event_id, String logger_name, String level_string, String thread_name, String caller_class,
			String caller_method, String formatted_message) {
		super();
		this.event_id = event_id;
		this.logger_name = logger_name;
		this.level_string = level_string;
		this.thread_name = thread_name;
		this.caller_class = caller_class;
		this.caller_method = caller_method;
		this.formatted_message = formatted_message;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getLogger_name() {
		return logger_name;
	}
	public void setLogger_name(String logger_name) {
		this.logger_name = logger_name;
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
	public String getCaller_class() {
		return caller_class;
	}
	public void setCaller_class(String caller_class) {
		this.caller_class = caller_class;
	}
	public String getCaller_method() {
		return caller_method;
	}
	public void setCaller_method(String caller_method) {
		this.caller_method = caller_method;
	}
	public String getFormatted_message() {
		return formatted_message;
	}
	public void setFormatted_message(String formatted_message) {
		this.formatted_message = formatted_message;
	}
	
	
}
