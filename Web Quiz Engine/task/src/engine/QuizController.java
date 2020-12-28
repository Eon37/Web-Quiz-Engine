package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public FeedBack answerQuiz(@RequestBody Answer answer, @PathVariable Long id) {
        return quizService.solve(answer, id);
    }

    @PostMapping(path = "/api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        quizService.save(quiz);
        return quiz;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizService.getById(id);
    }

    @GetMapping(path = "/api/quizzes")
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int pageSize) {
        return quizService.getAll(page, pageSize);
    }

    @GetMapping(path = "/api/quizzes/completed")
    public Page<CompletedQuizzes> getCompletedQuizzes(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int pageSize) {
        return quizService.getCompleted(page, pageSize);
    }


    @DeleteMapping(path = "/api/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Long id) {
        quizService.delete(quizService.getById(id));
    }
}
