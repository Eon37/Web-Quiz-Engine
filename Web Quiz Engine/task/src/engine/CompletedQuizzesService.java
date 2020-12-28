package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompletedQuizzesService {

    @Autowired
    CompletedQuizzesRepository completedQuizzesRepository;

    public Long maxId() {
        return completedQuizzesRepository.maxId();
    }

    public void save(CompletedQuizzes completedQuizzes) {
        Long max = maxId();
        completedQuizzes.setId(max == null ? 0 : max + 1);

        completedQuizzesRepository.save(completedQuizzes);
    }

    public Page<CompletedQuizzes> getByUser(User user, Pageable pageable) {
        return completedQuizzesRepository.findByUser(user, pageable);
    }

}
