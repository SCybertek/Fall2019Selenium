package com.automation.tests.day10;

public class NotesJSExecutor {
    /**
     * JavaScriptExecutor - interface that allows to execute JavaScript code as part of our selenium script.
     *
     * JavaScript - used for front-end development, and supported by every browser and website. If, something doesn't work in selenium, we can do it with JavaScriptExecutor. For example: click, scroll, text input.
     *
     * Since JavascriptExecutor is an interface, we cannot create an object of it. Instead, we can cast webdriver object.
     *
     * JavascriptExecutor js = (JavascriptExecutor) driver;
     *
     * Then, we can use executeScript method to run js code.
     *
     * ###This method, performs text input.
     *
     * js.executeScript("arguments[0].setAttribute('value', 'tomsmith')", webelement);
     *
     * ###THis method is used for click
     *
     * js.executeScript("arguments[0].click()", webelement);
     *
     * ###This element returns page title as a string
     *
     * js.executeScript("return document.title").toString();
     *
     * ###This function scrolls until webelement is visible
     *
     * js.executeScript("arguments[0].scrollIntoView(true)", link);
     */
}
