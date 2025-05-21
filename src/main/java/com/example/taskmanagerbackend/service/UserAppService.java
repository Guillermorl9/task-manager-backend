package com.example.taskmanagerbackend.service;

import com.example.taskmanagerbackend.model.CategoryApp;
import com.example.taskmanagerbackend.model.TaskApp;
import com.example.taskmanagerbackend.model.TaskListApp;
import com.example.taskmanagerbackend.model.UserApp;
import com.example.taskmanagerbackend.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserAppRepository userAppRepository;

    public UserApp getUserById(Long userId) {
        return userAppRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserApp createUser(UserApp userApp) {
        setParentReferences(userApp);
        return userAppRepository.save(userApp);
    }

    public void deleteUser(Long userId) {
        UserApp userApp = userAppRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userAppRepository.delete(userApp);
    }

    private void setParentReferences(UserApp user) {
        if (user.getCategories() != null) {
            for (CategoryApp category : user.getCategories()) {
                category.setUserApp(user);
                if (category.getLists() != null) {
                    for (TaskListApp list : category.getLists()) {
                        list.setCategory(category);
                        if (list.getTasks() != null) {
                            for (TaskApp task : list.getTasks()) {
                                task.setTaskListApp(list);
                            }
                        }
                    }
                }
            }
        }
    }
}

