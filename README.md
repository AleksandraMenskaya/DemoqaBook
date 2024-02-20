# Проект по автоматизации тестирования для Тинькофф Банка
«Тинько́фф Банк» — российский коммерческий банк, сфокусированный полностью на дистанционном обслуживании, не имеющий розничных отделений.

##  Содержание:

- <a href="#tools"> Используемые инструменты</a>
- <a href="#cases"> Тест-кейсы</a>
- <a href="#autotests"> Запуск автотестов</a>
- <a href="#jenkins"> Сборка в Jenkins</a>
- <a href="#allureReport"> Пример Allure-отчета</a>
- <a href="#allure"> Интеграция с Allure TestOps</a>
- <a href="#jira"> Интеграция с Jira</a>
- <a href="#tg"> Уведомления в Telegram с использованием бота</a>
- <a href="#video"> Видео примера запуска тестов в Selenoid</a>


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
<a href="https://www.atlassian.com/ru/software/jira/"><img width="5%" title="Jira" src="src/test/resources/icon/Jira.png"></a>
</p>

____
Тесты написаны на языке <code>Java</code> с использованием фреймворка для автоматизации тестирования [Selenide](https://selenide.org/), сборщик - <code>Gradle</code>.

<code>JUnit 5</code> задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для удаленного запуска используется [Selenoid](https://aerokube.com/selenoid/).

Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.
Также реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.


Содержание Allure-отчета для каждого кейса:
* Шаги теста и результат их выполнения
* Скриншот страницы на последнем шаге (возможность визуально проанализировать, почему упал тест)
* Page Source (возможность открыть source страницы в новой вкладке и посмотреть причину падения теста)
* Логи консоли браузера
* Видео выполнения автотеста.
____
<a id="cases"></a>
## :male_detective: Тест-кейсы
Auto:
- ✓ Валидация поля "Дата рождения"
- ✓ Проверка дизайнов карты
- ✓ Валидация заполнения прогресс бара
- ✓ Валидация поля электронная почта
- ✓ Скролл к форме карты Tinkoff Black
- ✓ Проверка, что первый элемент поисковой выдачи содержит текст запроса
- ✓ Отправка оценки
- ✓ Проверка локализации страницы для определенного url

  <a id="autotests"></a>
____
## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала

Локальный запуск.
Из корневой директории проекта выполнить:
```
gradle clean AllTest  запуск всех тестов
gradle clean LocalizationtinkoffblackTest  запуск тестов проверки локализации
gradle clean FormForDebitCardTest запуск тестов проверки для формы tinkoff black
gradle clean HelpPageTest запуск тестов проверки для страницы help
```
____
<a id="jenkins"></a>
## <img width="4%" style="vertical-align:middle" title="Jenkins" src="src/test/resources/icon/Jenkins.png"/> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/job/TinkoffTestUI/"> Jenkins </a>
Для доступа в Jenkins необходима регистрация на ресурсе [Jenkins](https://jenkins.autotests.cloud/)

Для запуска сборки необходимо перейти в раздел <code>Build with parameters</code>, выбрать необходимые параметры и нажать кнопку <code>Build</code>.
![build](src/test/resources/icon/Params_Jenkins.png "Сборка Jenkins")


###  Параметры сборки в Jenkins:
- TASK (набор тестов для запуска)
- URL (адрес основной страницы тестируемого сайта)
- SELENOID (адрес удаленного сервера, на котором будут выполняться тесты)
- SIZE (размер окна браузера, по умолчанию 1920x1080)
- VERSION (версия браузера, по умолчанию 100.0. Реализован запуск в Firefox на версиях 98.0 и 97.0, а также Chrome 99.0 и 100.0)
- BROWSER (браузер, по умолчанию chrome)
<p align="center">
<img title="parametrs" src="media/screen/parametrs.png">
</p>
<p>После выполнения сборки, в блоке <code>Build History</code> напротив номера сборки появятся значки <code>Allure Report</code> и <code>Allure TestOps</code>, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.</p>

![jenkins]("src/test/resources/icon/Params_Jenkins.png.png" "График Jenkins")

____
<a id="allureReport"></a>
## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/test/resources/icon/Allure_Report.png"/> Пример <a target="_blank" href="https://jenkins.autotests.cloud/job/ElenaMalysheva98-23-17/allure/"> Allure-отчета </a>


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

