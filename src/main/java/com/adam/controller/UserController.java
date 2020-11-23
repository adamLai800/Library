package com.adam.controller;

import com.adam.api.request.AddUserRequest;
import com.adam.api.request.DeleteByUserIdRequest;
import com.adam.api.response.AddUserResponse;
import com.adam.api.response.DeleteByUserIdResponse;
import com.adam.model.User;
import com.adam.service.UserService;
import com.adam.util.LibraryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/2.0/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //addUser
    @PostMapping(path = "/addUser") //
    public @ResponseBody
    AddUserResponse addUser(@RequestBody AddUserRequest addUserRequest) {
        boolean isSucceeded = false;
        User user = new User();
        user.setUserId(userService.getNewUserIdInsertTable());
        user.setUserName(addUserRequest.getUserName());
        isSucceeded = userService.addUser(user);
        AddUserResponse addUserResponse = new AddUserResponse();
        try {
            if (isSucceeded) {
                addUserResponse.setUser(user);
                addUserResponse.setCode(LibraryConstant.OK);
                addUserResponse.setMsg(LibraryConstant.ADD_USER_MSG);
            } else {
                addUserResponse.setCode(LibraryConstant.NO);
                addUserResponse.setErrorMsg(LibraryConstant.ADD_USER_ERROR);
            }
        } catch (Exception e) {
            addUserResponse.setCode(LibraryConstant.OTHER);
            addUserResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return addUserResponse;
    }

    //deleteByUserId
    @DeleteMapping(path = "/deleteByUserId")
    public @ResponseBody
    DeleteByUserIdResponse
    deleteByUserId(@RequestBody DeleteByUserIdRequest deleteByUserIdRequest) {
        boolean isSucceeded = false;
        isSucceeded = userService.deleteByUserId(deleteByUserIdRequest);
        DeleteByUserIdResponse deleteByUserIdResponse = new DeleteByUserIdResponse();
        try {
            if (isSucceeded) {
                deleteByUserIdResponse.setCode(LibraryConstant.OK);
                deleteByUserIdResponse.setMsg(LibraryConstant.DELETE_USER_MSG);
            } else {
                deleteByUserIdResponse.setCode(LibraryConstant.NO);
                deleteByUserIdResponse.setErrorMsg(LibraryConstant.DELETE_USER_ERROR);
            }
        } catch (Exception e) {
            deleteByUserIdResponse.setCode(LibraryConstant.OTHER);
            deleteByUserIdResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return deleteByUserIdResponse;
    }


    @GetMapping(path = "/getUserHistory")
    public @ResponseBody
    DeleteByUserIdResponse getUserHistoryResponse(@RequestBody DeleteByUserIdRequest deleteByUserIdRequest) {
        HashMap<String, ArrayList<UserRecordHistory>> getUserHistory =
                new HashMap<String, ArrayList<UserRecordHistory>>();

        ArrayList<UserRecordHistory> userRecordHistory = recordServiceImpl.getUserRecordHistory(userId);
        getUserHistory.put(userService.getUserName(userId), userRecordHistory);
        return getUserHistory;
    }

}
