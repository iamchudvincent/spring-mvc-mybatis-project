package service;

import dao.FeedbackMapper;

public interface FeedbackService<T> extends BaseService<T>,
        FeedbackMapper<T> {

}
