package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UpdateDto;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:5173")
public class UserController{
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping
	public List<User> getall(){
		return repo.findAll();
	}
	
	@PostMapping
      public User saveall(@RequestBody User user){
     return repo.save(user);
	}
	@DeleteMapping("/{id}") 
	public void deleteid(@PathVariable Long id){
		repo.deleteById(id);
	}
	@PutMapping("/update")
	public String updat(@RequestBody UpdateDto request){
		User user = repo.findById(request.getId()).orElse(null);
		if(user == null){
			return "User Not found";
		}
		else{
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			repo.save(user);
			return "user updated successfully";
		} 
	}
}