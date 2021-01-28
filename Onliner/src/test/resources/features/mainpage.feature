Feature: Dropdown list

  Scenario: Auto sales dropdown list displaying
    Given Main page is opened
    When User hovers on auto sales button
    Then Auto sales dropdown list is displayed
    And Auto sales dropdown list contains title "Автобарахолка"

  Scenario: Flat sales dropdown list displaying
    Given Main page is opened
    When User hovers on flat sales button
    Then Flat sales dropdown list is displayed
    And Flat sales dropdown list contains title "Продажа"