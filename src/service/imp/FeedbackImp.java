package service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.FeedbackService;
import dao.FeedbackMapper;
@Service("feedbackService")
public class FeedbackImp<T> extends BaseServiceImp<T> implements
		FeedbackService<T> {
	
	@Autowired
	private FeedbackMapper<T> mapper;

	public FeedbackMapper<T> getMapper() {
		return mapper;
	}
	
	

}
