Feature: Catalog page

  Scenario: Catalog categories displaying
    Given Catalog page is opened
    Then Catalog categories are displayed:
      | Электроника | Компьютеры и сети | Бытовая техника| Стройка и ремонт| Дом и сад |
      | Авто и мото | Красота и спорт   | Детям и мамам  | Работа и офис   | Еда       |

  Scenario: Computers and networks options displaying
    Given Catalog page is opened
    When Computers and networks category is selected
    Then Computers and networks options are displayed
      | Ноутбуки, компьютеры, мониторы | Комплектующие | Хранение данных | Сетевое оборудование |

  Scenario: Accessories offers have price and description
    Given Catalog page is opened
    When Computers and networks category is selected
    And Accessories option is selected
    Then Accessories offers have price and description

  Scenario Outline: Sort products by manufacturer
    Given Main page is opened
    When User selects product category <category>
    And User selects one manufacturer <manufacturer>
    Then Only products from selected manufacturer <manufacturer> are displayed
    Examples:
      |category           |manufacturer |
      |Телевизоры         |LG           |
      |Мобильные телефоны |Samsung      |