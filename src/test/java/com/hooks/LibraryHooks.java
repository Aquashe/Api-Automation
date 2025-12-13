package com.hooks;


import com.api.exceptions.ApiException;
import com.api.utils.GlobalData;
import com.api.utils.RuntimeVariable;
import com.context.ContextHolder;
import com.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LibraryHooks {
    @After("@CreateBook")
    public void afterAddBook(){
        String bookId = RuntimeVariable.get("bookId", String.class);
        GlobalData.bookIds.add(bookId);
        log.info("✅ Msg: {}",  RuntimeVariable.get("bookAddedMsg"));
        log.info("✅ ID : {}",  bookId);
    }

    @Before("@GetBookDetailsById")
    public void beforeGetBookDetailsById(){
        if(!RuntimeVariable.contains("bookId"))
            throw new ApiException("❌ BookId not found. AddBook scenario not executed.");
    }

    @Before("@GetBookDetailsByAuthor")
    public void beforeGetBookDetailsByAuthor(){
        if(!RuntimeVariable.contains("bookId"))
            throw new ApiException("❌ BookId not found. AddBook scenario not executed.");
    }

    @Before("@DeleteBook")
    public void beforeDeleteBook(){
        ScenarioContext context = ContextHolder.getContext();

        if(!RuntimeVariable.contains("bookId"))
            throw new ApiException("❌ BookId not found. AddBook scenario not executed.");
    }

    @After("@DeleteBook")
    public void afterDeleteBook(){
        log.info("✅ msg: {}",  RuntimeVariable.get("deleteBookMsg"));
    }

    @After
    public void cleanUp() {
        ContextHolder.clear();
    }
}
