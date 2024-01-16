package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@WebServlet("/book-load")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10)
public class BookLoaderServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("First time visiting the site");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Trying to download a book " + LocalDateTime.now());
    }

    @Override
    public void destroy() {
        System.out.println("Server destroy");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part filePart = req.getPart("file");
            String name = filePart.getSubmittedFileName();
            String uploadPath = "D:\\uploads";  // Задайте каталог для сохранения файлов

            String filePath = uploadPath + File.separator + name;
            filePart.write(filePath);

            resp.getWriter().print("The file uploaded successfully");
        } catch (Exception e) {
            resp.getWriter().print("Something went wrong: " + e.getMessage());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/loader.jsp").forward(req, resp);
    }
}
