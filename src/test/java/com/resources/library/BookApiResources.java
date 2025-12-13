package com.resources.library;

import com.resources.core.ApiResource;

public enum BookApiResources  implements ApiResource {
    AddBookAPI("Library/Addbook.php"),
    GetBookByIdAPI("Library/GetBook.php?ID={bookId}"),
    GetBookByAuthorNameAPI("Library/GetBook.php?AuthorName={authorName}"),
    DeleteBookAPI("Library/DeleteBook.php");

    private String resource;

    BookApiResources(String resourceName) {
        this.resource = resourceName;
    }

    @Override
    public String getResource() {
        return resource;
    }
}
