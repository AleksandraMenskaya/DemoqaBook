# Проект по автоматизации тестирования для DemoqaBook
DemoqaBook — тестовый сайт с несколькими разделами. Автоматизация выполнена для раздела Book Store Application.

##  Содержание:

- <a href="#tools"> Используемые инструменты</a>
- <a href="#cases"> Тест-кейсы</a>
- <a href="#autotests"> Запуск автотестов</a>
- <a href="#jenkins"> Сборка в Jenkins</a>
- <a href="#allureReport"> Пример Allure-отчета</a>
- <a href="#allure"> Интеграция с Allure TestOps</a>
- <a href="#video"> Видео примера запуска тестов в Selenoid для теста с UI</a>


____
<a id="tools"></a>
## Используемые инструменты

<p align="center">
<a href="https://www.java.com/"><img width="6%" title="Java" src="src/test/resources/icon/Java.png"></a>
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="src/test/resources/icon/Selenide.png"></a>
<a href="https://aerokube.com/selenoid/"><img width="6%" title="Selenoid" src="src/test/resources/icon/Selenoid.png"></a>
<a href="https://github.com/allure-framework/allure2"><img width="6%" title="Allure Report" src="src/test/resources/icon/Allure_Report.png"></a>
<a href="https://qameta.io/"><img width="5%" title="Allure TestOps" src="src/test/resources/icon/Allure_TestOps.png"></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="src/test/resources/icon/Gradle.png"></a>
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="src/test/resources/icon/JUnit5.png"></a>
<a href="https://github.com/"><img width="6%" title="GitHub" src="src/test/resources/icon/GitHub.svg"></a>
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="src/test/resources/icon/Jenkins.png"></a>
</p>

____
Тесты написаны на языке <code>Java</code> с использованием фреймворка для автоматизации тестирования [Selenide](https://selenide.org/), сборщик - <code>Gradle</code>.

<code>JUnit 5</code> задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для удаленного запуска используется [Selenoid](https://aerokube.com/selenoid/).

Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета.
Также реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.

Содержание Allure-отчета для каждого кейса:
* Шаги теста и результат их выполнения
* Request и Response.
* Для теста с UI:
    * Скриншот страницы на последнем шаге (возможность визуально проанализировать, почему упал тест)
    * Page Source (возможность открыть source страницы в новой вкладке и посмотреть причину падения теста)
    * Логи консоли браузера
    * Видео выполнения автотеста.
____
<a id="cases"></a>
## :male_detective: Тест-кейсы
Auto:
- ✓ Удаление книги через UI
- ✓ Получение авторизованным пользователем книги, которая есть в коллекции
- ✓ Получение книги неавторизованным пользователем
- ✓ Проверка успешной авторизации
- ✓ Проверка, что не отдается информация о пользователе другому пользователю

  <a id="autotests"></a>
____
## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала

Локальный запуск.
Из корневой директории проекта выполнить:
```
gradle clean AllTest -Denv=local запуск всех тестов
gradle clean BooksTest -Denv=local  запуск тестов проверок, связанных с книгами
gradle clean BooksTestWithUI -Denv=local запуск тестов проверки с UI
gradle clean LoginDemoqaBooksTest -Denv=local запуск тестов проверки авторизации
```
____
<a id="jenkins"></a>
## <img width="4%" style="vertical-align:middle" title="Jenkins" src="src/test/resources/icon/Jenkins.png"/> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/job/TinkoffTestUI/"> Jenkins </a>
Для доступа в Jenkins необходима регистрация на ресурсе [Jenkins](https://jenkins.autotests.cloud/job/DemoqaBook/)

Для запуска сборки необходимо перейти в раздел <code>Build with parameters</code>, выбрать необходимые параметры и нажать кнопку <code>Build</code>.

###  Параметры сборки в Jenkins:
- TASK (набор тестов для запуска)
<p align="center">
<img title="parametrs" src="test/resources/exampleFromTestRun/Params_Jenkins.png">
</p>
<p>После выполнения сборки, в блоке <code>Build History</code> напротив номера сборки появятся значки <code>Allure Report</code> и <code>Allure TestOps</code>, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.</p>

![jenkins]("src/test/resources/exampleFromTestRun/Params_Jenkins.png")

____
<a id="allureReport"></a>
## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/test/resources/icon/Allure_Report.png"/> Пример <a target="_blank" href="https://jenkins.autotests.cloud/job/DemoqaBook/allure/"> Allure-отчета </a>


<p align="center">
<img title="Allure Overview" src="src/test/resources/exampleFromTestRun/allure.png">
</p>

____
<a id="allure"></a>
## <img width="4%" style="vertical-align:middle" title="Allure TestOps" src="src/test/resources/icon/Allure_TestOps.png"/>  Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/3903/dashboards"> Allure TestOps </a>

На *Dashboard* в <code>Allure TestOps</code> видна статистика количества тестов: сколько из них добавлены и проходятся вручную, сколько автоматизированы. Новые тесты, а так же результаты прогона приходят по интеграции при каждом запуске сборки.

<p align="center">
<img title="Allure TestOps DashBoard" src="src/test/resources/exampleFromTestRun/test_ops.png">
</p>

![jenkins](src/test/resources/exampleFromTestRun/test_ops.png")

____
<a id="video"></a>
## <img width="4%" style="vertical-align:middle" title="Selenoid" src="src/test/resources/icon/Selenoid.png"/> Видео примера запуска тестов в Selenoid

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста
<p align="center">
 <img title="Selenoid Video" src="src/test/resources/exampleFromTestRun/gif1.gif">
</p>

