package TwitterBot.controller;

import TwitterBot.repository.TweetRepository;
import TwitterBot.model.Tweet;
import TwitterBot.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TweetController {
    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/tweet")
    public List<Tweet> getAllTweets(@RequestParam(value = "tag", required = false) Long tag) {
        if(tag != null){
            List<Tweet> tweet = tweetRepository.findByTag(tag);
            return tweet;
        }
        return tweetRepository.findAll();
    }

    @GetMapping("/tweet/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable(value = "id" ) Long tweetId) throws ResourceNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id ::" + tweetId));
        return ResponseEntity.ok().body(tweet);
    }

    @PostMapping("/employees")
    public Tweet createTweet(@Valid @RequestBody Tweet tweet){
        return tweetRepository.save(tweet);
    }

    /* @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
        Employee employee = tweetRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = tweetRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    } */

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteTweet(@PathVariable(value = "id") Long TweetId) throws ResourceNotFoundException{
        Tweet tweet = tweetRepository.findById(TweetId).orElseThrow(() -> new ResourceNotFoundException("Tweet not found for this id :: " + TweetId));

        tweetRepository.delete(tweet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}class Controller {
    
}
