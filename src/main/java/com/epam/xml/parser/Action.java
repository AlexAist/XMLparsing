package com.epam.xml.parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public interface Action {
    Optional<String> execute(HttpServletRequest request) throws IOException, ServletException;
}
