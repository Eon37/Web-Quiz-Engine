package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompletedQuizzesRepository extends PagingAndSortingRepository<CompletedQuizzes, Long> {
    @Query("select max(id) from Completed_Quizzes")
    Long maxId();

    @Query("select cq from Completed_Quizzes cq where cq.user = :user")
    Page<CompletedQuizzes> findByUser(@Param("user") User user, Pageable pageable);
}
