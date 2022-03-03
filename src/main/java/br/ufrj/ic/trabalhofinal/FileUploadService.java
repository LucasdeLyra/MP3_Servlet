package br.ufrj.ic.trabalhofinal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/api/upload")
@MultipartConfig
public class FileUploadService extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Recebe o Input do file
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Nome do Arquivo
        //InputStream fileContent = filePart.getInputStream();
        File uploads = new File("/Users/manoelsilva"); //Prepara a gravação no local escolhido
        File file = new File(uploads, fileName );
        try (InputStream fileContent = filePart.getInputStream()) {
            //MusicApplication.sucessoHTML();
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);//Grava no arquivo preparado
            //ToDo: criar um método para retornar um html e colocar aqui e talvez alguma exceção
            response.sendRedirect("/TrabalhoFinal-1.0-SNAPSHOT/api/listar?q="+fileName);
        }catch (IOException e){
            //MusicApplication.erroHTML();
        }
    }
}