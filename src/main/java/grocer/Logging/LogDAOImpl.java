package grocer.Logging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 

 
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */
public class LogDAOImpl{
 
    private JdbcTemplate jdbcTemplate;
 
    public LogDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
 
	
	public List<Log> list() {
		String sql = "SELECT * FROM logging_event";
		List<Log> listLog = jdbcTemplate.query(sql, new RowMapper<Log>() {
			 
	        @Override
	        public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Log aLog = new Log();
	            
	            aLog.setCaller_class(rs.getString("caller_class"));
	            aLog.setCaller_method(rs.getString("caller_method"));
	            aLog.setEvent_id(rs.getInt("event_id"));
	            aLog.setFormatted_message(rs.getString("formatted_message"));
	            aLog.setLevel_string(rs.getString("level_string"));
	            aLog.setLogger_name(rs.getString("logger_name"));
	            aLog.setThread_name(rs.getString("thread_name"));
	 
	            return aLog;
	        }
	 
	    });
	 
	    return listLog;
	}
 
	
	public List<ParsedLog> parsedList() throws SQLException {
		String sql = "SELECT * FROM logging_event WHERE level_string != ?";

		List<ParsedLog> listLog = jdbcTemplate.query(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "INFO");
				
			}
			
		},new RowMapper<ParsedLog>() {
        @Override
        public ParsedLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            ParsedLog aLog = new ParsedLog();
            
            aLog.setEvent_id(rs.getInt("event_id"));
            aLog.setFormatted_message(rs.getString("formatted_message")); 
            aLog.setLevel_string(rs.getString("level_string"));
            aLog.setThread_name(rs.getString("thread_name"));
 
            return aLog;
        }
 
    });
 
    return listLog;
		
		
	}
}
