package nus.project.FlowerMarket.controller;

import java.sql.*;
import java.io.*;
import java.nio.file.*;
import java.util.Optional;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import nus.project.FlowerMarket.model.Post;
import nus.project.FlowerMarket.repository.PostRepository;
import nus.project.FlowerMarket.service.FileUploadUtil;

@Controller
//change from post to market
@RequestMapping(path="/post")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(FlowerController.class);

    @Autowired
    private PostRepository postRepo;
    
    @GetMapping(path="/{postId}")
    public ModelAndView getPostById(@PathVariable Integer postId) {
        
        ModelAndView mvc = new ModelAndView();
        Optional<Post> opt = postRepo.getPostById(postId);
        mvc.addObject("post", opt.get());
        mvc.setViewName("post");
        mvc.setStatus(HttpStatus.OK);
        
        logger.info("\r\n Result  >>>>>" + mvc);
        
        return mvc;
        
    }

    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView getPost(@RequestParam MultipartFile image,
    @RequestPart String comment, 
    @RequestPart String poster) {

        ModelAndView mvc = new ModelAndView();
    
        String imageName = image.getOriginalFilename();
        long imageSize = image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Post f = new Post();
        f.setImage(buff);
        f.setComment(comment);
        f.setPoster(poster);
        f.setImageType(imageType); 
        
        mvc.setViewName("postresult");
        Integer updCount = postRepo.insertPost(f);
        mvc.addObject("updateCount", updCount);
        mvc.setStatus(HttpStatus.OK);
        
        return mvc;
    }

    
    @PostMapping(path="/market/save") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView getPost(Post post,
            @RequestParam("photo") MultipartFile image, @RequestPart String comment, 
            @RequestPart String poster) throws IOException {
        
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Post p = new Post();
        post.setImage(buff);
        p.setComment(comment);
        p.setPoster(poster);
        p.setImageType(imageType);

        Post savedPost = postRepo.getPost(post).get();

        String uploadDir = "market-photos/" + savedPost.getPostId();

        FileUploadUtil.saveFile(uploadDir, fileName, image);
        
        return new RedirectView("/market", true);
    }


//     public static void main(String[] args) {
//     //URL to Connect to the database
//     String url = "jdbc:mysql://db-mysql-sgp1-59175-do-user-11291487-0.b.db.ondigitalocean.com:25060/myflowermarket?ssl-mode=REQUIRED";
//     //Username
//     String user = "doadmin";
//     //Password
//     String password = "doadmin";
//     try{
//        //create the database connection string
//        Connection con = DriverManager.getConnection(url, user, password);
       
//        //object to create the request
//        Statement st = con.createStatement();
       
//        //execute the request
//        ResultSet rs = st.executeQuery("select * from post");
       
//        int i = 0;
//        while (rs.next()) {
//          InputStream input = rs.getBinaryStream(1);
//          //create the image in the current directory
//          OutputStream output = new FileOutputStream(new File("test"+i+".jpg"));
//          i++;
//          int b = 0;
//          while ((b = input.read()) > -1) {
//              output.write(b);
//          }
         
//          //close the OutputStream
//          output.close();
//          //close the InputStream
//          input.close();
//        }
       
//        //close the connection
//        con.close(); 
//     }catch (Exception e){
//       System.out.println(e.getMessage());
//     }
//   }
}
