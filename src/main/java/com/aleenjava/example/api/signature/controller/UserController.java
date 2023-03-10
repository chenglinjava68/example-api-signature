package com.aleenjava.example.api.signature.controller;

import com.aleenjava.example.api.signature.bean.ApiResult;
import com.aleenjava.example.api.signature.bean.User;
import com.aleenjava.example.api.signature.bean.UserRequest;
import com.aleenjava.example.api.signature.sign.aop.ApiSignValid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author cheng
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @GetMapping("/hello")
    public ApiResult<String> hello() {
        return ApiResult.success("hello");
    }

    @ApiSignValid
    @PostMapping
    public ApiResult<User> add(@RequestBody UserRequest userRequest) {
        User user = new User();
        int uid = atomicInteger.decrementAndGet();
        user.setUid(uid);
        BeanUtils.copyProperties(userRequest, user);
        // 模拟入库
        users.put(uid, user);
        return ApiResult.success(user);
    }

    /**
     * 读取用户
     *
     * @param uid
     * @return
     */
    @ApiSignValid
    @GetMapping("/{uid}")
    public ApiResult get(@PathVariable Integer uid) {
        User user = users.get(uid);
        if (user == null) {
            return ApiResult.fail("404", "not found user");
        }
        return ApiResult.success(user);
    }

}
