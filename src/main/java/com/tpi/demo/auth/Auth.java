package com.tpi.demo.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** <em>이 클래스는 AUTH 클래스입니다.</em>
 */
@Deprecated
public class Auth implements AuthService {

    // List <= 사이즈가 변할 수 있는 배열
    private final List<User> userList;
    private User signedUser;

    public Auth() {
        this.userList = new ArrayList<>();
    }

    @Override
    public void register(User user) {
        userList.add(user);
    }

    @Override
    public void login(User user) {

        User matchedUser = userList.stream()
                .filter((item) -> item.getUsername().equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다"));

        boolean isPasswordMatched = Objects.equals(matchedUser.getPassword(), user.getPassword());

        if (!isPasswordMatched) {
            throw new RuntimeException("아이디 또는 비밀번호가 잘못되었습니다");
        }

        signedUser = matchedUser;
    }

}
