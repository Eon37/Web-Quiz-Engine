package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompletedQuizzesService completedQuizzesService;

    public void save(Quiz quiz) {
        Long max = maxId();
        quiz.setId(max == null ? 0 : max + 1);

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        engine.User user = userRepository.findByEmail(authUser.getUsername());

        quiz.setUser(user);
        user.setQuizzes().add(quiz);

        quizRepository.save(quiz);
    }

    public FeedBack solve(Answer answer, Long id) {
        Quiz quiz = getById(id);

        if (answer.getAnswer().equals(quiz.getAnswer())) {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            engine.User user = userRepository.findByEmail(authUser.getUsername());

            CompletedQuizzes cquizzes = new CompletedQuizzes(user, quiz, new Timestamp(System.currentTimeMillis()));

            completedQuizzesService.save(cquizzes);

            return new FeedBack(true);
        }

        return new FeedBack(false);
    }

//    public List<Quiz> getAll(int pageNo, int pageSize) {
//
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//
//        Page<Quiz> pagingRez = quizRepository.findAll(paging);
//
//        return pagingRez.hasContent() ? pagingRez.getContent() : new ArrayList<>();
//    }

    public Page<Quiz> getAll(int pageNo, int pageSize) {
        return quizRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    public Page<CompletedQuizzes> getCompleted(int pageNo, int pageSize) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return completedQuizzesService.getByUser(userRepository.findByEmail(user.getUsername()),
                PageRequest.of(pageNo, pageSize, Sort.by("completedAt").descending()));
    }

    public Quiz getById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return quiz;
    }

    public void delete(Quiz quiz) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (!user.getUsername().equals(quiz.getUser().getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        quizRepository.delete(quiz);
    }

    public Long maxId() {
        return quizRepository.maxId();
    }
}
