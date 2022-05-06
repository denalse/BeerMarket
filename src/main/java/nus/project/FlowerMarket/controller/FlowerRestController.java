// package nus.project.FlowerMarket.controller;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping(path="/community/post")
// public class FlowerRestController {
    
//     // @Autowired
//     // private PostRepo postRepo;

//     // @GetMapping(path="/{postId}/image")
//     // public ResponseEntity<byte[]> getPostImage(@PathVariable Integer postId) {

//     //     Optional<Post> opt =  postRepo.getPostById(postId);

//     //     if(opt.isEmpty())
//     //         return ResponseEntity.notFound().build();

//     //     final Post postVal = opt.get();
//     //     HttpHeaders headers = new HttpHeaders();
//     //     headers.add("Content-Type", postVal.getImageType());
//     //     headers.add("Cache-control", "max-age=604800");

//     //     return ResponseEntity.ok()
//     //         .headers(headers)
//     //         .body(postVal.getImage());
//     // }
// }
