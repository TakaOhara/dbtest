package com.example.dbtest.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 全てのControllerで共通処理を定義
 */
@ControllerAdvice
public class WebMvcControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Stringの空文字をNULLに
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
