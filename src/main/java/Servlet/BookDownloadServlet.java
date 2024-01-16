package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebServlet("/book")
public class BookDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");

        if (fileName != null && !fileName.isEmpty()) {
            String folderPath = "D:\\uploads";
            String filePath = folderPath + File.separator + fileName;

            File file = new File(filePath);

            if (file.exists()) {
                String originalFileName = file.getName(); // получаем оригинальное имя файла
                response.setContentType("text/plain; charset=UTF-8");
                response.setContentLengthLong(file.length());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()) + "\"");

                try (InputStream fileInputStream = new FileInputStream(file);
                     OutputStream outputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

            } else {
                response.getWriter().write("File not found.");
            }
        } else {
            String folderPath = "D:\\uploads";
            File folder = new File(folderPath);
            File[] fileList = folder.listFiles();

            request.setAttribute("fileList", Arrays.asList(fileList));
            getServletContext().getRequestDispatcher("/downloader.jsp").forward(request, response);
        }
    }
}