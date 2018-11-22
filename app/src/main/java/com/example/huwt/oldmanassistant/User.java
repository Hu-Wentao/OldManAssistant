package com.example.huwt.oldmanassistant;


public class User {
    private static final String TAG = "User";
    public static final String TEST_ACCOUNT = "123";
    public static final String TEST_PWD = "456";
    public static boolean isLogin = false;
    /**
     * 判断账户密码是否正确
     * @param account 用户名
     * @param password 密码
     * @return 密码是否正确
     */
    static boolean checkAccountAndPwd(String account, String password){
        if(TEST_ACCOUNT.equals(account) && TEST_PWD.equals(password)){
            return true;
        }else{
            return false;
        }

    }
}
