package com.example.jbdl.ewallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Topic name
    private static final String USER_CREATE = "user_create";

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate; // Topic Name, Value (Key is not required for our use-case)

    @Autowired
    ObjectMapper objectMapper;

    public User create(User user) throws JsonProcessingException {
        user = userRepository.save(user);
        // TODO: Add code for adding some amount in wallet
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());
        jsonObject.put("userEmail", user.getEmail());
        jsonObject.put("userContact", user.getContact());

        kafkaTemplate.send(USER_CREATE, objectMapper.writeValueAsString(jsonObject));
        return user; 
    }
}
