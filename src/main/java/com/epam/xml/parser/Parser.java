package com.epam.xml.parser;

import com.epam.xml.Validator.Validator;
import com.epam.xml.exception.BasicException;
import com.epam.xml.factory.Builder;
import com.epam.xml.factory.Factory;
import com.epam.xml.factory.Type;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

public class Parser implements Action {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Optional<String> execute(HttpServletRequest request) throws IOException, ServletException {
        String page;
        String parserType = request.getParameter("parser");
        Part filePart = request.getPart("xml_file");
        String fileName = Paths.get(filePart.getName()).getFileName().toString();
        InputStream inputContent = filePart.getInputStream();
        File uploads = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        File file = File.createTempFile("xml-", ".xml", uploads);
        Files.copy(inputContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        if (Validator.isFileExist(file.toPath().toString())) {
            Factory factory = null;
            switch (parserType) {
                case "SAX": {
                    factory = Builder.createBuilder(Type.SAX);
                    try {
                        factory.buildBanks(Files.newInputStream(Paths.get(file.toPath().toString())));
                    } catch (BasicException e) {
                        LOG.log(Level.ERROR, "SAX error" + e);
                    }
                    break;
                }
                case "DOM": {
                    factory = Builder.createBuilder(Type.DOM);
                    try {
                        factory.buildBanks(Files.newInputStream(Paths.get(file.toPath().toString())));
                    } catch (BasicException e) {
                        LOG.log(Level.ERROR, "DOM error" + e);
                    }
                    break;
                }
                case "StAX": {
                    factory = Builder.createBuilder(Type.STAX);
                    try {
                        factory.buildBanks(Files.newInputStream(Paths.get(file.toPath().toString())));
                    } catch (BasicException e) {
                        LOG.log(Level.ERROR, "STAX error" + e);
                    }
                    break;
                }
            }
            if (factory != null) {
                request.setAttribute("banks", factory.getBanks());
            }
            page = "/front/output.jsp";
        } else {
            request.setAttribute("fileName", fileName);
            page = "/front/error.jsp";
        }
        return Optional.of(page);
    }
}
