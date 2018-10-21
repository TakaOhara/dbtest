package com.example.dbtest.controllers;

import com.example.dbtest.validator.InstructorExists;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class InstructorForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    @NotEmpty(message = "空白は不可")
    @Email(message = "メールアドレスを入力してください")
    @InstructorExists(message = "そのメールはすでに存在します")
    private String email;

    @NotEmpty(message = "空白は不可")
    private String youtubeChannel;

    private String hobby;

    public boolean isNewInstructor;

    private boolean validEmail;

    @AssertTrue(message = "emailとyoutubeは同じにしてください")
    public boolean isValidEmail() {
        if (this.email == null) return false;//初回アクセス時に必要　無いとnull例外
        if (this.youtubeChannel == null) return false;

        return this.email.equals(this.youtubeChannel);
    }

    public InstructorForm() {
        //isNewInstructor = true;//新規の場合は新規投稿フォームを表示
    }

    public InstructorForm(String firstName, String lastName, String email, String youtubeChannel, String hobby, boolean isNewInstructor) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
        this.isNewInstructor = isNewInstructor;
    }
}
