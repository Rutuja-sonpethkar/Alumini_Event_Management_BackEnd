package com.example.demo.Reposotry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.Model.Feedback;

@Repository
public class FeedbackRepo {

	List<Feedback> list;
	private final JdbcTemplate template;

	public FeedbackRepo(JdbcTemplate template) {
		this.template = template;
	}

	public boolean isAddnewfeedback(Feedback feedback) {
		String query = "INSERT INTO feedback (rating,  description, feedback_date, sid) VALUES (?, ?, ?, ?)";

		int value = template.update(query, ps -> {
			ps.setInt(1, feedback.getRating());
			ps.setString(2, feedback.getDescription());
			ps.setString(3, feedback.getFeedbackDate());
		//	ps.setInt(4, feedback.getSid());
		});

		return value > 0;
	}

	public List<Feedback> getAllFeedback() {
		String query = "SELECT * FROM feedback";

		List<Feedback> list = template.query(query, (rs, rowNum) -> {

			Feedback fd = new Feedback();
			fd.setFid(rs.getInt(1));
			fd.setRating(rs.getInt(2));
			fd.setDescription(rs.getString(3));
			fd.setFeedbackDate(rs.getString(4));
			fd.setSid(rs.getInt(5));

			return fd;
		});

		return list;
	}

	public boolean isdeleteById(int fid) {
		int value = template.update("delete from feedback where fid=" + fid);
		return value > 0 ? true : false;

	}

	public boolean isupdatefeedback(Feedback feedback) {
		String query = "update feedback set rating=?,  description=?,feedback_date=?,sid =? where fid=?";

		int value = template.update(query, (ps) -> {
			ps.setInt(1, feedback.getRating());
			ps.setString(2, feedback.getDescription());
			ps.setString(3, feedback.getFeedbackDate());
			ps.setInt(4, feedback.getSid());
			ps.setInt(5, feedback.getFid());
		});
		return value > 0 ? true : false;

	}

}